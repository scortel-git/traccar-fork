/*
 * Copyright 2015 Irving Gonzalez
 * Copyright 2015 Anton Tananaev (anton.tananaev@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.traccar.protocol;

import org.traccar.Context;
import org.traccar.StringProtocolEncoder;
import org.traccar.helper.Log;
import org.traccar.model.Command;
import org.traccar.model.Device;

public class TotemProtocolEncoder extends StringProtocolEncoder {

    @Override
    protected Object encodeCommand(Command command) {

        command.set(Command.KEY_DEVICE_PASSWORD, "000000");
        if (Context.getDeviceManager() != null) {
            Device device = Context.getDeviceManager().getDeviceById(command.getDeviceId());
            if (device != null) {
                if (device.getAttributes().containsKey(Command.KEY_DEVICE_PASSWORD)) {
                    command.set(Command.KEY_DEVICE_PASSWORD, device.getAttributes().get(Command.KEY_DEVICE_PASSWORD).toString());
                }
            }
        }

        switch (command.getType()) {
            //Assuming PIN 8 (Output C) is the power wire, like manual says but it can be PIN 5,7,8
            case Command.TYPE_ENGINE_STOP:
                return formatCommand(command, "*{%s},025,C,1#", Command.KEY_DEVICE_PASSWORD);
            case Command.TYPE_ENGINE_RESUME:
                return formatCommand(command, "*{%s},025,C,0#", Command.KEY_DEVICE_PASSWORD);
            default:
                Log.warning(new UnsupportedOperationException(command.getType()));
                break;
        }

        return null;
    }
}
