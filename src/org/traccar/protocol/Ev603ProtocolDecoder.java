/*
 * Copyright 2012 Anton Tananaev (anton.tananaev@gmail.com)
 *                Luis Parada (luis.parada@gmail.com)
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

import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.traccar.BaseProtocolDecoder;
import org.traccar.ServerManager;
import org.traccar.helper.Log;
import org.traccar.model.Position;

/**
 * Ev603 Protocol Decoder
 */
public class Ev603ProtocolDecoder extends BaseProtocolDecoder{

    /**
     * Device ID
     */
    private Long deviceId;

    /**
     * Initialize
     */
    public Ev603ProtocolDecoder(ServerManager serverManager) {
        super(serverManager);
    }

    /**
     * Regular expressions pattern
     */
    static private Pattern pattern = Pattern.compile(
            "!A," +                           // Type
            "(\\d{2})\\/(\\d{2})\\/(\\d{2})," + // Date dd/mm/YY
            "(\\d{2}):(\\d{2}):(\\d{2})," +   // Time hh:mm:ss
            "(-?\\d+\\.\\d+)," +              // Latitude (DDMM.MMMM)
            "(-?\\d+\\.\\d+)," +              // Longitude (DDDMM.MMMM)
            "(\\d+\\.\\d+)," +                // Speed
            "(\\d+\\.?\\d+)," +               // Course
            ".*");

    /**
     * Decode message
     */
    protected Object decode(
            ChannelHandlerContext ctx, Channel channel, Object msg)
            throws Exception {

        String sentence = (String) msg;

        // Detect device ID
        if (sentence.startsWith("!1,")) {
            String imei = sentence.substring(3);
            try {
                deviceId = getDataManager().getDeviceByImei(imei).getId();
            } catch(Exception error) {
                Log.warning("Unknown device - " + imei);
                return null;
            }
        }

        else if (sentence.startsWith("!A,")) {
            // Parse message
            Matcher parser = pattern.matcher(sentence);
            if (!parser.matches()) {
                return null;
            }

            // Create new position
            Position position = new Position();
            position.setDeviceId(deviceId);
            StringBuilder extendedInfo = new StringBuilder("<protocol>ev603</protocol>");
            Integer index = 1;

            // Date
            Calendar time = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            time.clear();
            time.set(Calendar.DAY_OF_MONTH, Integer.valueOf(parser.group(index++)));
            time.set(Calendar.MONTH, Integer.valueOf(parser.group(index++)) - 1);
            time.set(Calendar.YEAR, 2000 + Integer.valueOf(parser.group(index++)));

            // Time
            time.set(Calendar.HOUR, Integer.valueOf(parser.group(index++)));
            time.set(Calendar.MINUTE, Integer.valueOf(parser.group(index++)));
            time.set(Calendar.SECOND, Integer.valueOf(parser.group(index++)));
            position.setTime(time.getTime());

            // Validity
            position.setValid(true);

            // Coordinates
            position.setLatitude(Double.valueOf(parser.group(index++)));
            position.setLongitude(Double.valueOf(parser.group(index++)));

            // Altitude
            position.setAltitude(0.0);

            // Speed
            position.setSpeed(Double.valueOf(parser.group(index++)));

            // Course
            position.setCourse(Double.valueOf(parser.group(index++)));

            // Extended info
            position.setExtendedInfo(extendedInfo.toString());

            return position;
        }

        return null;
    }
}
