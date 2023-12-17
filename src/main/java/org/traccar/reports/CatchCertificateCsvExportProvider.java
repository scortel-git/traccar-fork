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
import org.traccar.model.ElbCatchCertificate;
import org.traccar.model.ElbCatchCertificate;
import org.traccar.storage.Storage;
import org.traccar.storage.StorageException;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CatchCertificateCsvExportProvider {

    private final Storage storage;

    @Inject
    public CatchCertificateCsvExportProvider(Storage storage) {
        this.storage = storage;
    }

    public void generate(
            OutputStream outputStream, long deviceId, Date from, Date to) throws StorageException {

        var catchCertificates = PriorNotificationUtil.getCatchCertificate(storage, deviceId, from, to);

        var attributes = catchCertificates.stream()
                .flatMap((catchCertificate -> catchCertificate.getAttributes().keySet().stream()))
                .collect(Collectors.toUnmodifiableSet());

        var properties = new LinkedHashMap<String, Function<ElbCatchCertificate, Object>>();
        properties.put("id", ElbCatchCertificate::getId);
        properties.put("protocol", ElbCatchCertificate::getProtocol);
        properties.put("tripNumber", ElbCatchCertificate::getTripNumber);
        properties.put("landingTime", catchCertificate -> DateUtil.formatDate(catchCertificate.getLandingTime()));
        properties.put("serverTime", catchCertificate -> DateUtil.formatDate(catchCertificate.getServerTime()));
        properties.put("deviceTime", catchCertificate -> DateUtil.formatDate(catchCertificate.getDeviceTime()));
        properties.put("fixTime", catchCertificate -> DateUtil.formatDate(catchCertificate.getFixTime()));
        properties.put("valid", ElbCatchCertificate::getValid);
        properties.put("latitude", catchCertificate -> DistanceCalculator.decimalToDMS(catchCertificate.getLatitude(), "latitude"));
        properties.put("longitude", catchCertificate -> DistanceCalculator.decimalToDMS(catchCertificate.getLongitude(), "longitude"));
        properties.put("altitude", ElbCatchCertificate::getAltitude);
        properties.put("speed", ElbCatchCertificate::getSpeed);
        properties.put("course", ElbCatchCertificate::getCourse);
        properties.put("LandingPortId", ElbCatchCertificate::getLandingPortId);
        properties.put("LandingPortCode", ElbCatchCertificate::getLandingPortCode);
        properties.put("DeparturePortId", ElbCatchCertificate::getDeparturePortId);
        properties.put("DeparturePortCode", ElbCatchCertificate::getDeparturePortCode);
        properties.put("deviceId", ElbCatchCertificate::getDeviceId);


        attributes.forEach(key -> properties.put(key, catchCertificate -> catchCertificate.getAttributes().get(key)));

        try (PrintWriter writer = new PrintWriter(outputStream)) {
            writer.println(String.join(",", properties.keySet()));
            catchCertificates.forEach(catchCertificate -> writer.println(properties.values().stream()
                    .map(f -> Objects.toString(f.apply(catchCertificate), ""))
                    .collect(Collectors.joining(","))));
        }
    }

}
