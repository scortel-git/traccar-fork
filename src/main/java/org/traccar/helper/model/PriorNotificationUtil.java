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

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public final class PriorNotificationUtil {

    private PriorNotificationUtil() {
    }

    public static boolean isLatest(CacheManager cacheManager, PriorNotification notification) {
        PriorNotification lastPriorNotification = cacheManager.getPriorNotification(notification.getDeviceId());
        return lastPriorNotification == null || notification.getFixTime().compareTo(lastPriorNotification.getFixTime()) >= 0;
    }

    public static double calculateDistance(PriorNotification first, PriorNotification last, boolean useOdometer) {
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

    public static List<ElbEndFishingTrip> getPriorNotifications(
            Storage storage, long deviceId, Date from, Date to) throws StorageException {
        return storage.getObjects( ElbEndFishingTrip.class, new Request(
                new Columns.All(),
                new Condition.And(
                        new Condition.Equals("deviceId", deviceId),
                        new Condition.Between("fixTime", "from", from, "to", to)),
                new Order("fixTime")));
    }

    public static List<ElbEndFishingTrip> getLatestPriorNotifications(Storage storage, long userId) throws StorageException {
        var devices = storage.getObjects(Device.class, new Request(
                new Columns.Include("id"),
                new Condition.Permission(User.class, userId, Device.class)));

        var deviceIds = devices.stream().map(BaseModel::getId).collect(Collectors.toUnmodifiableSet());

        var priorNotifications = storage.getObjects(ElbEndFishingTrip.class, new Request(
                new Columns.All(),
                new Condition.Permission(User.class, userId, Device.class)));

        return priorNotifications.stream()
                .filter(priorNotification -> deviceIds.contains(priorNotification.getDeviceId()))
                .collect(Collectors.toList());
    }
}
