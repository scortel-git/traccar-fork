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

import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.traccar.BaseProtocol;
import org.traccar.CharacterDelimiterFrameDecoder;
import org.traccar.PipelineBuilder;
import org.traccar.TrackerServer;
import org.traccar.model.Command;

import java.util.List;

public class MiniFinderProtocol extends BaseProtocol {

    public MiniFinderProtocol() {
        setSupportedDataCommands(
                Command.TYPE_SET_TIMEZONE,
                Command.TYPE_VOICE_MONITORING,
                Command.TYPE_ALARM_SPEED,
                Command.TYPE_ALARM_GEOFENCE,
                Command.TYPE_ALARM_VIBRATION,
                Command.TYPE_SET_AGPS,
                Command.TYPE_ALARM_FALL,
                Command.TYPE_MODE_POWER_SAVING,
                Command.TYPE_MODE_DEEP_SLEEP,
                Command.TYPE_SOS_NUMBER,
                Command.TYPE_SET_INDICATOR);
    }

    @Override
    public void initTrackerServers(List<TrackerServer> serverList) {
        serverList.add(new TrackerServer(false, getName()) {
            @Override
            protected void addProtocolHandlers(PipelineBuilder pipeline) {
                pipeline.addLast(new CharacterDelimiterFrameDecoder(1024, ';'));
                pipeline.addLast(new StringEncoder());
                pipeline.addLast(new StringDecoder());
                pipeline.addLast(new MiniFinderProtocolEncoder());
                pipeline.addLast(new MiniFinderProtocolDecoder(MiniFinderProtocol.this));
            }
        });
    }

}
