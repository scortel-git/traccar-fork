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
package org.traccar.helper.model;

import org.traccar.model.*;
import org.traccar.session.cache.CacheManager;
import org.traccar.storage.Storage;
import org.traccar.storage.StorageException;
import org.traccar.storage.query.Columns;
import org.traccar.storage.query.Condition;
import org.traccar.storage.query.Order;
import org.traccar.storage.query.Request;

import java.util.*;
import java.util.stream.Collectors;

public final class ElbUtil {

    private ElbUtil() {
    }

    public static boolean isLatest(CacheManager cacheManager, ElbMessage elbMessage) {
        PriorNotification lastPriorNotification = cacheManager.getPriorNotification(elbMessage.getDeviceId());
        return lastPriorNotification == null || elbMessage.getFixTime().compareTo(lastPriorNotification.getFixTime()) >= 0;
    }

    public static double calculateDistance(ElbMessage first, ElbMessage last, boolean useOdometer) {
        double distance;
        double firstOdometer = first.getDouble(Position.KEY_ODOMETER);
        double lastOdometer = last.getDouble(Position.KEY_ODOMETER);

        if (useOdometer && firstOdometer != 0.0 && lastOdometer != 0.0) {
            distance = lastOdometer - firstOdometer;
        } else {
            distance = last.getDouble(Position.KEY_TOTAL_DISTANCE) - first.getDouble(Position.KEY_TOTAL_DISTANCE);
        }
        return distance;
    }

    public static List<ElbMessage> getElbMessages(Storage storage, long deviceId, Date from, Date to) throws StorageException {
        return storage.getObjects(ElbMessage.class, new Request(
                new Columns.All(),
                new Condition.And(
                        new Condition.Equals("deviceId", deviceId),
                        new Condition.Between("fixTime", "from", from, "to", to)),
                new Order("fixTime")));
    }

    public static List<ElbMessage> getLatestElbMessages(Storage storage, long userId) throws StorageException {
        var devices = storage.getObjects(Device.class, new Request(
                new Columns.Include("id"),
                new Condition.Permission(User.class, userId, Device.class)));

        var deviceIds = devices.stream().map(BaseModel::getId).collect(Collectors.toUnmodifiableSet());

        var elbMessages = storage.getObjects(ElbMessage.class, new Request(
                new Columns.All(),
                new Condition.Permission(User.class, userId, Device.class)));

        return elbMessages.stream()
                .filter(priorNotification -> deviceIds.contains(priorNotification.getDeviceId()))
                .collect(Collectors.toList());
    }

    public static Device getDeviceByIdStorage(Storage storage, long deviceId) throws StorageException {

        return storage.getObject(Device.class, new Request(
                new Columns.All(), new Condition.Equals("id", deviceId)));
    }

    public static List<ElbMessage> getOldElbMessages(Storage storage, String tripNumber) throws StorageException {

        return storage.getObjects(ElbMessage.class,
                new Request(
                        new Columns.All(), new Condition.And(
                        new Condition.Equals("tripNumber", tripNumber),
                        new Condition.Equals("outdated", false)
                )
                )
        );
    }

    public static List<ElbEndFishingTrip> getOldEndFishingTrips(Storage storage, String tripNumber) throws StorageException {

        return storage.getObjects(ElbEndFishingTrip.class,
                new Request(
                        new Columns.All(), new Condition.And(
                        new Condition.Equals("tripNumber", tripNumber),
                        new Condition.Equals("outdated", false)
                )
                )
        );
    }

    public static boolean getIsDuplicated(List<ElbEndFishingTrip> oldElbEndFishingTrips, ElbEndFishingTrip entity) {
        for (ElbEndFishingTrip previous : oldElbEndFishingTrips) {
            if (Objects.equals(((ElbEndFishingTrip) entity).getDeviceTime().toString(), previous.getDeviceTime().toString())) {
                return true;
            }
        }
        return false;
    }

    public static <T> void updateElbMessages(Storage storage, List<T> elbMessages) throws StorageException {

        for (T elbMessage : elbMessages) {
            storage.updateObject(elbMessage, new Request(
                    new Columns.Exclude("id"),
                    new Condition.Equals("id", ((ElbMessage) elbMessage).getId())));
        }
    }
    public static <T> void updateElbMessages(Storage storage, T elbMessage) throws StorageException {

            storage.updateObject(elbMessage, new Request(
                    new Columns.Exclude("id"),
                    new Condition.Equals("id", ((ElbMessage) elbMessage).getId())));

    }

    public static ElbEndFishingTrip handlePreviousEndFishingTrips(Storage storage, List<ElbEndFishingTrip> oldElbEndFishingTrips, ElbEndFishingTrip entity) throws StorageException {
        List<ElbMessage> elbMessages = new ArrayList<>();
        for (ElbEndFishingTrip previous : oldElbEndFishingTrips) {
            entity.setUniqueNumber(previous.getUniqueNumber());
            previous.setOutdated(true);
            elbMessages.add(previous);
        }

        updateElbMessages(storage, elbMessages);


        return entity;
    }

    public static long getCountElbMessages(Storage storage, ElbMessage elbMessage, long deviceId) throws StorageException {

        var conditions = new LinkedList<Condition>();

        conditions.add(new Condition.Equals("deviceId", deviceId));
        conditions.add(new Condition.Equals("outdated", false));

        return storage.getObjectsCount(elbMessage.getClass(),
                new Request(
                        new Columns.All(),
                        Condition.merge(conditions)
                )
        );
    }

    public static long getDriverId(Storage storage) throws StorageException {
        List<Permission> permissions = storage.getPermissions(Device.class, Driver.class);
        if (!permissions.isEmpty()) {
            return permissions.get(0).getPropertyId();
        }

        return 0;
    }
}
