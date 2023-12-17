/*
 * Copyright 2015 - 2022 Anton Tananaev (anton@traccar.org)
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
package org.traccar.api.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import org.traccar.api.BaseResource;
import org.traccar.helper.model.DeviceUtil;
import org.traccar.helper.model.PriorNotificationUtil;
import org.traccar.model.Device;
import org.traccar.model.ElbCatchCertificate;
import org.traccar.reports.CatchCertificateCsvExportProvider;
import org.traccar.storage.StorageException;
import org.traccar.storage.query.Columns;
import org.traccar.storage.query.Condition;
import org.traccar.storage.query.Request;

import java.util.*;

@Path("catch-certificates")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatchCertificateResource extends BaseResource {


    @Inject
    private CatchCertificateCsvExportProvider csvExportProvider;


    @GET
    public Collection<ElbCatchCertificate> getJson(
            @QueryParam("deviceId") List<Long> deviceIds,
            @QueryParam("id") List<Long> catchCertificateIds,
            @QueryParam("from") Date from,
            @QueryParam("to") Date to,
            @QueryParam("groupId") List<Long> groupIds,
            @QueryParam("isNotValid") boolean isNotValid
    )
            throws StorageException {
        var conditions = new LinkedList<Condition>();

        if (!catchCertificateIds.isEmpty()) {
            var catchCertificates = new ArrayList<ElbCatchCertificate>();
            for (long catchCertificateId : catchCertificateIds) {
                ElbCatchCertificate ElbCatchCertificate = storage.getObject(ElbCatchCertificate.class, new Request(
                        new Columns.All(), new Condition.Equals("id", catchCertificateId)));
                permissionsService.checkPermission(Device.class, getUserId(), ElbCatchCertificate.getDeviceId());
                catchCertificates.add(ElbCatchCertificate);
            }
            return catchCertificates;
        } else if (!deviceIds.isEmpty()) {
            ArrayList<ElbCatchCertificate> result = new ArrayList<>();
            for (Device device: DeviceUtil.getAccessibleDevices(storage, getUserId(), deviceIds, groupIds)) {
                result.addAll(PriorNotificationUtil.getCatchCertificate(storage, device.getId(), from, to));
            }
            return result;
        } else {
            return PriorNotificationUtil.getLatestElbCatchCertificate(storage, getUserId());
        }
    }
    @Path("csv")
    @GET
    @Produces("text/csv")
    public Response getCsv(
            @QueryParam("deviceId") long deviceId,
            @QueryParam("from") Date from, @QueryParam("to") Date to) throws StorageException {
        permissionsService.checkPermission(Device.class, getUserId(), deviceId);
        StreamingOutput stream = output -> {
            try {
                csvExportProvider.generate(output, deviceId, from, to);
            } catch (StorageException e) {
                throw new WebApplicationException(e);
            }
        };
        return Response.ok(stream)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=catch-certificates.csv").build();
    }

}
