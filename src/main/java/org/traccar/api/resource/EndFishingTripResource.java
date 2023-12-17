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
import org.traccar.helper.model.PriorNotificationUtil;
import org.traccar.model.Device;
import org.traccar.model.ElbEndFishingTrip;
import org.traccar.model.UserRestrictions;
import org.traccar.reports.PriorNotificationCsvExportProvider;
import org.traccar.storage.StorageException;
import org.traccar.storage.query.Columns;
import org.traccar.storage.query.Condition;
import org.traccar.storage.query.Request;

import java.util.*;

@Path("end-fishing-trip")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EndFishingTripResource extends BaseResource {


    @Inject
    private PriorNotificationCsvExportProvider csvExportProvider;


    @GET
    public Collection<ElbEndFishingTrip> getJson(
            @QueryParam("deviceId") long deviceId,
            @QueryParam("id") List<Long> endFishingTripsIds,
            @QueryParam("from") Date from,
            @QueryParam("to") Date to,
            @QueryParam("isNotValid") boolean isNotValid
    )
            throws StorageException {
        var conditions = new LinkedList<Condition>();

        if (!endFishingTripsIds.isEmpty()) {
            var endFishingTrips = new ArrayList<ElbEndFishingTrip>();
            for (long prioNotificationId : endFishingTripsIds) {
                ElbEndFishingTrip elbEndFishingTrip = storage.getObject(ElbEndFishingTrip.class, new Request(
                        new Columns.All(), new Condition.Equals("id", prioNotificationId)));
                permissionsService.checkPermission(Device.class, getUserId(), elbEndFishingTrip.getDeviceId());
                endFishingTrips.add(elbEndFishingTrip);
            }
            return endFishingTrips;
        } else if (deviceId > 0) {
            permissionsService.checkPermission(Device.class, getUserId(), deviceId);
            if (from != null && to != null) {
                permissionsService.checkRestriction(getUserId(), UserRestrictions::getDisableReports);
                return PriorNotificationUtil.getEndFishingTrips(storage, deviceId, from, to);
            } else {
                conditions.add(new Condition.Equals("deviceId", deviceId));
                conditions.add(new Condition.Equals("outdated", false));
                if (isNotValid){
                    return storage.getObjects(ElbEndFishingTrip.class, new Request(
                            new Columns.All(),
                            Condition.merge(conditions)
                            ));
                }
                conditions.add(new Condition.Equals("valid", true));

                return storage.getObjects(ElbEndFishingTrip.class, new Request(
                        new Columns.All(),
                        Condition.merge(conditions)
                ));

            }
        } else {
            return PriorNotificationUtil.getLatestEndFishingTrips(storage, getUserId());
        }
    }
    @Path("update")
    @GET
    public Response getJson(
            @QueryParam("id") List<Long> endFishingTripsIds,
            @DefaultValue("-1")
            @QueryParam("valid") int valid

            )throws StorageException {
       if (permissionsService.notAdmin(getUserId())) {
           return Response.status(Response.Status.FORBIDDEN).build();
       }
       boolean isValid;
       if (valid == 1) {
           isValid = true;
       } else if (valid == 0) {
           isValid = false;
       }
       else {
           return Response.status(Response.Status.BAD_REQUEST).build();
       }

        var endFishingTrips = new ArrayList<ElbEndFishingTrip>();
        if (!endFishingTripsIds.isEmpty()) {
            for (long prioNotificationId : endFishingTripsIds) {
                ElbEndFishingTrip elbEndFishingTrip = storage.getObject(ElbEndFishingTrip.class, new Request(
                        new Columns.All(), new Condition.Equals("id", prioNotificationId)));
                endFishingTrips.add(elbEndFishingTrip);
            }
        }
        if (!endFishingTrips.isEmpty()) {
            for (ElbEndFishingTrip trip : endFishingTrips) {
                trip.setValid(isValid);
                storage.updateObject(trip, new Request(
                        new Columns.Exclude("id"),
                        new Condition.Equals("id", trip.getId()))
                );
            }
        }

        return Response.status(Response.Status.ACCEPTED).build();

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

}
