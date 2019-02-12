/*
 * Copyright 2015 - 2018 Anton Tananaev (anton@traccar.org)
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
package org.traccar.protocol;

import org.traccar.BaseProtocol;
import org.traccar.PipelineBuilder;
import org.traccar.TrackerServer;
import org.traccar.model.Command;

import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

public class WatchProtocol extends BaseProtocol {

    public WatchProtocol() {
        setSupportedDataCommands(
                Command.TYPE_CUSTOM,
                Command.TYPE_POSITION_SINGLE,
                Command.TYPE_POSITION_PERIODIC,
                Command.TYPE_SOS_NUMBER,
                Command.TYPE_ALARM_SOS,
                Command.TYPE_ALARM_BATTERY,
                Command.TYPE_REBOOT_DEVICE,
                Command.TYPE_ALARM_REMOVE,
                Command.TYPE_SILENCE_TIME,
                Command.TYPE_ALARM_CLOCK,
                Command.TYPE_SET_PHONEBOOK,
                Command.TYPE_MESSAGE,
                Command.TYPE_VOICE_MESSAGE,
                Command.TYPE_SET_TIMEZONE,
                Command.TYPE_SET_INDICATOR);
        addServer(new TrackerServer(false, getName()) {
            @Override
            protected void addProtocolHandlers(PipelineBuilder pipeline) {
                pipeline.addLast(new WatchFrameDecoder());
                pipeline.addLast(new StringEncoder(StandardCharsets.ISO_8859_1));
                pipeline.addLast(new WatchProtocolEncoder());
                pipeline.addLast(new WatchProtocolDecoder(WatchProtocol.this));
            }
        });
    }

}
