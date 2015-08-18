/*
 * Copyright 2015 Amila Silva
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
package org.traccar;

import org.traccar.helper.DistanceCalculator;
import org.traccar.model.Event;
import org.traccar.model.Position;

import java.math.BigDecimal;

public class DistanceHandler extends BaseDataHandler {

    private Position getLastPosition(long deviceId) {
        if (Context.getConnectionManager() != null) {
            return Context.getConnectionManager().getLastPosition(deviceId);
        }
        return null;
    }

    public Position calculateDistance(Position position) {

        double distance = 0.0;

        Position last = getLastPosition(position.getDeviceId());
        if (last != null) {
            if (last.getOther().containsKey(Event.KEY_DISTANCE)) {
                distance = ((Number) last.getOther().get(Event.KEY_DISTANCE)).doubleValue();
            }

            distance += DistanceCalculator.distance(
                    position.getLatitude(), position.getLongitude(),
                    last.getLatitude(), last.getLongitude());

            distance = BigDecimal.valueOf(distance).setScale(2).doubleValue();
        }

        position.set(Event.KEY_DISTANCE, distance);
        return position;
    }

    @Override
    protected Position handlePosition(Position position) {
        return calculateDistance(position);
    }

}
