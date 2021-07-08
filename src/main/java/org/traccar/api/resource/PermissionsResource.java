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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.traccar.Context;
import org.traccar.api.BaseResource;
import org.traccar.helper.LogAction;
import org.traccar.model.Device;
import org.traccar.model.Permission;
import org.traccar.model.User;

@Path("permissions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PermissionsResource  extends BaseResource {

    private void checkPermission(Permission permission, boolean link) {
        if (!link && permission.getOwnerClass().equals(User.class)
                && permission.getPropertyClass().equals(Device.class)) {
            if (getUserId() != permission.getOwnerId()) {
                Context.getPermissionsManager().checkUser(getUserId(), permission.getOwnerId());
            } else {
                Context.getPermissionsManager().checkAdmin(getUserId());
            }
        } else {
            Context.getPermissionsManager().checkPermission(
                    permission.getOwnerClass(), getUserId(), permission.getOwnerId());
        }
        Context.getPermissionsManager().checkPermission(
                permission.getPropertyClass(), getUserId(), permission.getPropertyId());
    }

    private void refreshPermissions(List<LinkedHashMap<String, Long>> entities) {
        Set<String> ownerProperty = new HashSet<>();
        entities.stream()
            .map(e -> {
                try {
                    return new Permission(e);
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .filter(p -> ownerProperty.add(p.getOwnerClass().getName() + p.getPropertyClass().getName()))
            .forEach(p -> Context.getPermissionsManager().refreshPermissions(p));
    }

    @POST
    public Response add(LinkedHashMap<String, Long> entity) throws SQLException, ClassNotFoundException {
        return add(Stream.of(entity).collect(Collectors.toList()));
    }

    @Path("bulk")
    @POST
    public Response add(List<LinkedHashMap<String, Long>> entities) throws SQLException, ClassNotFoundException {
        Context.getPermissionsManager().checkReadonly(getUserId());
        for (LinkedHashMap<String, Long> entity: entities) {
            Permission permission = new Permission(entity);
            checkPermission(permission, true);
            Context.getDataManager().linkObject(permission.getOwnerClass(), permission.getOwnerId(),
                    permission.getPropertyClass(), permission.getPropertyId(), true);
            LogAction.link(getUserId(), permission.getOwnerClass(), permission.getOwnerId(),
                    permission.getPropertyClass(), permission.getPropertyId());
        }
        refreshPermissions(entities);
        return Response.noContent().build();
    }

    @DELETE
    public Response remove(LinkedHashMap<String, Long> entity) throws SQLException, ClassNotFoundException {
        return remove(Stream.of(entity).collect(Collectors.toList()));
    }

    @DELETE
    @Path("bulk")
    public Response remove(List<LinkedHashMap<String, Long>> entities) throws SQLException, ClassNotFoundException {
        Context.getPermissionsManager().checkReadonly(getUserId());
        for (LinkedHashMap<String, Long> entity: entities) {
            Permission permission = new Permission(entity);
            checkPermission(permission, false);
            Context.getDataManager().linkObject(permission.getOwnerClass(), permission.getOwnerId(),
                    permission.getPropertyClass(), permission.getPropertyId(), false);
            LogAction.unlink(getUserId(), permission.getOwnerClass(), permission.getOwnerId(),
                    permission.getPropertyClass(), permission.getPropertyId());
        }
        refreshPermissions(entities);
        return Response.noContent().build();
    }
}
