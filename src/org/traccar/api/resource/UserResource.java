/*
 * Copyright 2015 - 2016 Anton Tananaev (anton@traccar.org)
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
import org.traccar.model.User;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Collection;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource extends BaseResource {

    @GET
    public Collection<User> get() throws SQLException {
        Context.getPermissionsManager().checkAdmin(getUserId());
        return Context.getPermissionsManager().getUsers();
    }

    @PermitAll
    @POST
    public Response add(User entity) throws SQLException {
        if (!Context.getPermissionsManager().isAdmin(getUserId())) {
            Context.getPermissionsManager().checkRegistration(getUserId());
        }
        Context.getPermissionsManager().addUser(entity);
        if (Context.getNotificationManager() != null) {
            Context.getNotificationManager().refresh();
        }
        return Response.ok(entity).build();
    }

    @Path("{id}")
    @PUT
    public Response update(@PathParam("id") long id, User entity) throws SQLException {
        User old = Context.getPermissionsManager().getUser(entity.getId());
        if (old.getExpirationTime() == null && entity.getExpirationTime() != null
                || old.getExpirationTime() != null && !old.getExpirationTime().equals(entity.getExpirationTime())
                || old.getAdmin() != entity.getAdmin()
                || old.getReadonly() != entity.getReadonly()
                || old.getDisabled() != entity.getDisabled()
                || old.getDeviceLimit() != entity.getDeviceLimit()
                || old.getToken() == null && entity.getToken() != null
                || old.getToken() != null && !old.getToken().equals(entity.getToken())) {
            Context.getPermissionsManager().checkAdmin(getUserId());
        } else {
            Context.getPermissionsManager().checkUser(getUserId(), entity.getId());
        }
        Context.getPermissionsManager().updateUser(entity);
        if (Context.getNotificationManager() != null) {
            Context.getNotificationManager().refresh();
        }
        return Response.ok(entity).build();
    }

    @Path("{id}")
    @DELETE
    public Response remove(@PathParam("id") long id) throws SQLException {
        Context.getPermissionsManager().checkUser(getUserId(), id);
        Context.getPermissionsManager().removeUser(id);
        if (Context.getGeofenceManager() != null) {
            Context.getGeofenceManager().refreshUserGeofences();
        }
        if (Context.getNotificationManager() != null) {
            Context.getNotificationManager().refresh();
        }
        return Response.noContent().build();
    }

}
