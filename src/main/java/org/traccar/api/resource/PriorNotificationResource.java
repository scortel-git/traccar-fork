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
import org.traccar.helper.model.PositionUtil;
import org.traccar.helper.model.PriorNotificationUtil;
import org.traccar.model.Device;
import org.traccar.model.Position;
import org.traccar.model.PriorNotification;
import org.traccar.model.UserRestrictions;
import org.traccar.reports.CsvExportProvider;
import org.traccar.reports.GpxExportProvider;
import org.traccar.reports.KmlExportProvider;
import org.traccar.storage.StorageException;
import org.traccar.storage.query.Columns;
import org.traccar.storage.query.Condition;
import org.traccar.storage.query.Request;

import java.util.*;

@Path("prior-notifications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PriorNotificationResource extends BaseResource {

    @Inject
    private KmlExportProvider kmlExportProvider;

    @Inject
    private CsvExportProvider csvExportProvider;

    @Inject
    private GpxExportProvider gpxExportProvider;

    @GET
    public Collection<PriorNotification> getJson(
            @QueryParam("deviceId") long deviceId,
            @QueryParam("id") List<Long> priorNotificationsIds,
            @QueryParam("from") Date from,
            @QueryParam("to") Date to)
            throws StorageException {
        if (!priorNotificationsIds.isEmpty()) {
            var priorNotifications = new ArrayList<PriorNotification>();
            for (long prioNotificationId : priorNotificationsIds) {
                PriorNotification priorNotification = storage.getObject(PriorNotification.class, new Request(
                        new Columns.All(), new Condition.Equals("id", prioNotificationId)));
                permissionsService.checkPermission(Device.class, getUserId(), priorNotification.getDeviceId());
                priorNotifications.add(priorNotification);
            }
            return priorNotifications;
        } else if (deviceId > 0) {
            permissionsService.checkPermission(Device.class, getUserId(), deviceId);
            if (from != null && to != null) {
                permissionsService.checkRestriction(getUserId(), UserRestrictions::getDisableReports);
                return PriorNotificationUtil.getPriorNotifications(storage, deviceId, from, to);
            } else {
                return storage.getObjects(PriorNotification.class, new Request(
                        new Columns.All(), new Condition.LatestPositions(deviceId)));
            }
        } else {
            return PriorNotificationUtil.getLatestPriorNotifications(storage, getUserId());
        }
    }

    @DELETE
    public Response remove(
            @QueryParam("deviceId") long deviceId,
            @QueryParam("from") Date from, @QueryParam("to") Date to) throws StorageException {
        permissionsService.checkPermission(Device.class, getUserId(), deviceId);
        permissionsService.checkRestriction(getUserId(), UserRestrictions::getReadonly);

        var conditions = new LinkedList<Condition>();
        conditions.add(new Condition.Equals("deviceId", deviceId));
        conditions.add(new Condition.Between("fixTime", "from", from, "to", to));
        storage.removeObject(PriorNotification.class, new Request(Condition.merge(conditions)));

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Path("prior-kml")
    @GET
    @Produces("application/vnd.google-earth.kml+xml")
    public Response getKml(
            @QueryParam("deviceId") long deviceId,
            @QueryParam("from") Date from, @QueryParam("to") Date to) throws StorageException {
        permissionsService.checkPermission(Device.class, getUserId(), deviceId);
        StreamingOutput stream = output -> {
            try {
                kmlExportProvider.generate(output, deviceId, from, to);
            } catch (StorageException e) {
                throw new WebApplicationException(e);
            }
        };
        return Response.ok(stream)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=prior-notifications.kml").build();
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
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=prior-notifications.csv").build();
    }

    @Path("prior-gpx")
    @GET
    @Produces("application/gpx+xml")
    public Response getGpx(
            @QueryParam("deviceId") long deviceId,
            @QueryParam("from") Date from, @QueryParam("to") Date to) throws StorageException {
        permissionsService.checkPermission(Device.class, getUserId(), deviceId);
        StreamingOutput stream = output -> {
            try {
                gpxExportProvider.generate(output, deviceId, from, to);
            } catch (StorageException e) {
                throw new WebApplicationException(e);
            }
        };
        return Response.ok(stream)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=prior-notifications.gpx").build();
    }

}
