/*
 * Copyright 2022 Anton Tananaev (anton@traccar.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.traccar.session.cache;

import org.traccar.model.Attribute;
import org.traccar.model.BaseModel;
import org.traccar.model.Device;
import org.traccar.model.Driver;
import org.traccar.model.Geofence;
import org.traccar.model.GroupedModel;
import org.traccar.model.Maintenance;
import org.traccar.model.Notification;
import org.traccar.model.Server;
import org.traccar.model.User;
import org.traccar.storage.Storage;
import org.traccar.storage.StorageException;
import org.traccar.storage.query.Columns;
import org.traccar.storage.query.Condition;
import org.traccar.storage.query.Request;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

@Singleton
public class CacheManager {

    private static final Collection<Class<? extends BaseModel>> CLASSES = Arrays.asList(
            Attribute.class, Driver.class, Geofence.class, Maintenance.class, Notification.class);

    private final Storage storage;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Map<CacheKey, CacheValue> deviceCache = new HashMap<>();
    private final Map<Long, Map<Class<? extends BaseModel>, List<Long>>> deviceLinks = new HashMap<>();

    private Server server;
    private final Map<Long, List<User>> notificationUsers = new HashMap<>();

    @Inject
    public CacheManager(Storage storage) throws StorageException {
        this.storage = storage;
        invalidateServer();
        invalidateUsers();
    }

    public <T extends BaseModel> T getObject(Class<T> clazz, long id) {
        try {
            lock.readLock().lock();
            var cacheValue = deviceCache.get(new CacheKey(clazz, id));
            return cacheValue != null ? cacheValue.getValue() : null;
        } finally {
            lock.readLock().unlock();
        }
    }

    public <T extends BaseModel> List<T> getDeviceObjects(long deviceId, Class<T> clazz) {
        try {
            lock.readLock().lock();
            return deviceLinks.get(deviceId).get(clazz).stream()
                    .map(id -> deviceCache.get(new CacheKey(clazz, id)).<T>getValue())
                    .collect(Collectors.toList());
        } finally {
            lock.readLock().unlock();
        }
    }

    public Server getServer() {
        try {
            lock.readLock().lock();
            return server;
        } finally {
            lock.readLock().unlock();
        }
    }

    public List<User> getNotificationUsers(long notificationId) {
        try {
            lock.readLock().lock();
            return notificationUsers.get(notificationId);
        } finally {
            lock.readLock().unlock();
        }
    }

    public Driver findDriverByUniqueId(long deviceId, String driverUniqueId) {
        return getDeviceObjects(deviceId, Driver.class).stream()
                .filter(driver -> driver.getUniqueId().equals(driverUniqueId))
                .findFirst()
                .orElse(null);
    }

    public void addDevice(long deviceId) throws StorageException {
        try {
            lock.writeLock().lock();
            if (!deviceLinks.containsKey(deviceId)) {
                unsafeAddDevice(deviceId);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void removeDevice(long deviceId) {
        try {
            lock.writeLock().lock();
            if (deviceLinks.containsKey(deviceId)) {
                unsafeRemoveDevice(deviceId);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public <T extends BaseModel> void updateOrInvalidate(Class<T> clazz, long id) throws StorageException {
        var object = storage.getObject(clazz, new Request(
                new Columns.All(), new Condition.Equals("id", "id", id)));
        if (object != null) {
            updateOrInvalidate(object);
        } else {
            invalidate(clazz, id);
        }
    }

    public <T extends BaseModel> void updateOrInvalidate(T object) throws StorageException {
        boolean invalidate = false;
        var before = getObject(object.getClass(), object.getId());
        if (before == null) {
            return;
        } else if (object instanceof GroupedModel) {
            if (((GroupedModel) before).getGroupId() != ((GroupedModel) object).getGroupId()) {
                invalidate = true;
            }
        }
        if (invalidate) {
            invalidate(object.getClass(), object.getId());
        } else {
            // TODO if device, also need to update geofences
            try {
                lock.writeLock().lock();
                deviceCache.get(new CacheKey(object.getClass(), object.getId())).setValue(object);
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    public <T extends BaseModel> void invalidate(Class<T> clazz, long id) throws StorageException {
        invalidate(new CacheKey(clazz, id));
    }

    public void invalidate(
            Class<? extends BaseModel> clazz1, long id1,
            Class<? extends BaseModel> clazz2, long id2) throws StorageException {
        invalidate(new CacheKey(clazz1, id1), new CacheKey(clazz2, id2));
    }

    private void invalidateServer() throws StorageException {
        server = storage.getObject(Server.class, new Request(new Columns.All()));
    }

    private void invalidateUsers() throws StorageException {
        Map<Long, User> users = new HashMap<>();
        storage.getObjects(User.class, new Request(new Columns.All()))
                .forEach(user -> users.put(user.getId(), user));
        storage.getPermissions(User.class, Notification.class).forEach(permission -> {
            long notificationId = permission.getPropertyId();
            var user = users.get(permission.getOwnerId());
            notificationUsers.computeIfAbsent(notificationId, k -> new LinkedList<>()).add(user);
        });
    }

    private void addObject(long deviceId, BaseModel object) {
        deviceCache.computeIfAbsent(new CacheKey(object), k -> new CacheValue(object)).retain(deviceId);
    }

    private void unsafeAddDevice(long deviceId) throws StorageException {
        Map<Class<? extends BaseModel>, List<Long>> links = new HashMap<>();

        addObject(deviceId, storage.getObject(Device.class, new Request(
                new Columns.All(), new Condition.Equals("id", "id", deviceId))));

        for (Class<? extends BaseModel> clazz : CLASSES) {
            var objects = storage.getObjects(clazz, new Request(
                    new Columns.All(), new Condition.Permission(Device.class, deviceId, clazz)));
            links.put(clazz, objects.stream().map(BaseModel::getId).collect(Collectors.toList()));
            objects.forEach(object -> addObject(deviceId, object));
        }

        var users = storage.getObjects(User.class, new Request(
                new Columns.All(), new Condition.Permission(User.class, Device.class, deviceId)));
        links.put(User.class, users.stream().map(BaseModel::getId).collect(Collectors.toList()));
        for (var user : users) {
            var notifications = storage.getObjects(Notification.class, new Request(
                    new Columns.All(), new Condition.Permission(User.class, user.getId(), Notification.class)));
            notifications.stream()
                    .filter(Notification::getAlways)
                    .forEach(object -> {
                        links.computeIfAbsent(Notification.class, k -> new LinkedList<>()).add(object.getId());
                        addObject(deviceId, object);
                    });
        }

        deviceLinks.put(deviceId, links);
    }

    private void unsafeRemoveDevice(long deviceId) {
        deviceCache.remove(new CacheKey(Device.class, deviceId));
        deviceLinks.remove(deviceId).forEach((clazz, ids) -> ids.forEach(id -> {
            var key = new CacheKey(clazz, id);
            deviceCache.computeIfPresent(key, (k, value) -> {
                value.release(deviceId);
                return value.getReferences().size() > 0 ? value : null;
            });
        }));
    }

    private void invalidate(CacheKey... keys) throws StorageException {
        try {
            lock.writeLock().lock();
            unsafeInvalidate(keys);
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void unsafeInvalidate(CacheKey[] keys) throws StorageException {
        boolean invalidateServer = false;
        boolean invalidateUsers = false;
        Set<Long> linkedDevices = new HashSet<>();
        for (var key : keys) {
            if (key.classIs(Server.class)) {
                invalidateServer = true;
            } else {
                if (key.classIs(User.class) || key.classIs(Notification.class)) {
                    invalidateUsers = true;
                }
                deviceCache.computeIfPresent(key, (k, value) -> {
                    linkedDevices.addAll(value.getReferences());
                    return value;
                });
            }
        }
        for (long deviceId : linkedDevices) {
            unsafeRemoveDevice(deviceId);
            unsafeAddDevice(deviceId);
        }
        if (invalidateServer) {
            invalidateServer();
        }
        if (invalidateUsers) {
            invalidateUsers();
        }
    }

}
