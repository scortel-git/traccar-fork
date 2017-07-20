/*
 * Copyright 2017 Anton Tananaev (anton@traccar.org)
 * Copyright 2017 Andrey Kunitsyn (andrey@traccar.org)
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

import java.sql.SQLException;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.traccar.Context;
import org.traccar.api.BaseResource;

@Path("permissions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PermissionsResource  extends BaseResource {

    @Path("/{slave : (users|devices|groups|geofences|drivers|attributes|calendars)}")
    @POST
    public Response add(Map<String, Long> entity) throws SQLException {
        Context.getPermissionsManager().checkReadonly(getUserId());
        if (entity.size() != 2) {
            throw new IllegalArgumentException();
        }
        for (String key : entity.keySet()) {
            Context.getPermissionsManager().checkPermission(key.replace("Id", ""), getUserId(), entity.get(key));
        }
        Context.getDataManager().linkObject(entity, true);
        Context.getPermissionsManager().refreshPermissions(entity);
        return Response.noContent().build();
    }

    @Path("/{slave : (users|devices|groups|geofences|drivers|attributes|calendars)}")
    @DELETE
    public Response remove(Map<String, Long> entity) throws SQLException {
        Context.getPermissionsManager().checkReadonly(getUserId());
        if (entity.size() != 2) {
            throw new IllegalArgumentException();
        }
        for (String key : entity.keySet()) {
            Context.getPermissionsManager().checkPermission(key.replace("Id", ""), getUserId(), entity.get(key));
        }
        Context.getDataManager().linkObject(entity, false);
        Context.getPermissionsManager().refreshPermissions(entity);
        return Response.noContent().build();
    }

}
