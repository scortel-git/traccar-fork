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

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.traccar.api.BaseObjectResource;

import org.traccar.model.ElbPorts;
import org.traccar.storage.StorageException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Path("ports")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PortResource extends BaseObjectResource<ElbPorts> {

    public PortResource() {
        super(ElbPorts.class);
    }

    @GET
    public Collection<ElbPorts> get(
            @QueryParam("id") List<Long> portIds) throws StorageException {
        if (!portIds.isEmpty()) {
            List<ElbPorts> result = new LinkedList<>();
            for (Long portId : portIds) {
                result.add(ElbPorts.elbPortsHashMap.get(portId));
            }
            return result;
        }else {
            Collection<ElbPorts> values = ElbPorts.elbPortsHashMap.values();
            return new LinkedList<>(values);

        }
    }

}
