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
import org.traccar.model.ElbSpecies;
import org.traccar.storage.StorageException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Path("fishes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FishResource extends BaseObjectResource<ElbSpecies> {

    public FishResource() {
        super(ElbSpecies.class);
    }

    @GET
    public Collection<ElbSpecies> get(
            @QueryParam("id") List<Short> fishIds) throws StorageException {
        if (!fishIds.isEmpty()) {
            List<ElbSpecies> result = new LinkedList<>();
            for (short fishId : fishIds) {
                result.add(ElbSpecies.elbSpecies.get(fishId));
            }
            return result;
        }else {
            Collection<ElbSpecies> values = ElbSpecies.elbSpecies.values();
            return new LinkedList<>(values);

        }
    }

}
