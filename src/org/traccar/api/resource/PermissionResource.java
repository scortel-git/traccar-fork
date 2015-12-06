/*
 * Copyright 2015 Anton Tananaev (anton.tananaev@gmail.com)
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

import org.traccar.Context;
import org.traccar.api.BaseResource;
import org.traccar.model.Device;
import org.traccar.model.Permission;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Collection;

@Path("permissions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PermissionResource extends BaseResource {

    @POST
    public Response add(Permission entity) {
        try {
            Context.getDataManager().linkDevice(entity.getUserId(), entity.getDeviceId());
            return Response.ok(entity).build();
        } catch (SQLException e) {
            throw new WebApplicationException(e);
        }
    }

    @DELETE
    public Response remove(Permission entity) {
        try {
            Context.getDataManager().unlinkDevice(entity.getUserId(), entity.getDeviceId());
            return Response.noContent().build();
        } catch (SQLException e) {
            throw new WebApplicationException(e);
        }
    }

}
