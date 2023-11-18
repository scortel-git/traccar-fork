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
package org.traccar.reports;

import jakarta.inject.Inject;
import org.traccar.helper.DateUtil;
import org.traccar.helper.DistanceCalculator;
import org.traccar.helper.model.PriorNotificationUtil;
import org.traccar.model.ElbEndFishingTrip;

import org.traccar.storage.Storage;
import org.traccar.storage.StorageException;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PriorNotificationCsvExportProvider {

    private final Storage storage;

    @Inject
    public PriorNotificationCsvExportProvider(Storage storage) {
        this.storage = storage;
    }

    public void generate(
            OutputStream outputStream, long deviceId, Date from, Date to) throws StorageException {

        var priorNotifications = PriorNotificationUtil.getPriorNotifications(storage, deviceId, from, to);

        var attributes = priorNotifications.stream()
                .flatMap((priorNotification -> priorNotification.getAttributes().keySet().stream()))
                .collect(Collectors.toUnmodifiableSet());

        var properties = new LinkedHashMap<String, Function<ElbEndFishingTrip, Object>>();
        properties.put("id", ElbEndFishingTrip::getId);
        properties.put("protocol", ElbEndFishingTrip::getProtocol);
        properties.put("uniqueNumber", ElbEndFishingTrip::getUniqueNumber);
        properties.put("tripNumber", ElbEndFishingTrip::getTripNumber);
        properties.put("endFishingTripTime", priorNotification -> DateUtil.formatDate(priorNotification.getEndFishingTripTime()));
        properties.put("estimatedArriveTime", priorNotification -> DateUtil.formatDate(priorNotification.getEstimatedArriveTime()));
        properties.put("serverTime", priorNotification -> DateUtil.formatDate(priorNotification.getServerTime()));
        properties.put("deviceTime", priorNotification -> DateUtil.formatDate(priorNotification.getDeviceTime()));
        properties.put("fixTime", priorNotification -> DateUtil.formatDate(priorNotification.getFixTime()));
        properties.put("valid", ElbEndFishingTrip::getValid);
        properties.put("latitude", priorNotification -> DistanceCalculator.decimalToDMS(priorNotification.getLatitude(), "latitude"));
        properties.put("longitude", priorNotification -> DistanceCalculator.decimalToDMS(priorNotification.getLongitude(), "longitude"));
        properties.put("altitude", ElbEndFishingTrip::getAltitude);
        properties.put("speed", ElbEndFishingTrip::getSpeed);
        properties.put("course", ElbEndFishingTrip::getCourse);
        properties.put("LandingPortId", ElbEndFishingTrip::getLandingPortId);
        properties.put("deviceId", ElbEndFishingTrip::getDeviceId);


        attributes.forEach(key -> properties.put(key, priorNotification -> priorNotification.getAttributes().get(key)));

        try (PrintWriter writer = new PrintWriter(outputStream)) {
            writer.println(String.join(",", properties.keySet()));
            priorNotifications.forEach(priorNotification -> writer.println(properties.values().stream()
                    .map(f -> Objects.toString(f.apply(priorNotification), ""))
                    .collect(Collectors.joining(","))));
        }
    }

}
