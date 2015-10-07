/*
 * Copyright 2012 Alex Wilson <alex@uq.edu.au>
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

import java.net.SocketAddress;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jboss.netty.channel.Channel;
import org.traccar.BaseProtocolDecoder;
import org.traccar.model.Position;

/**
 * Maxon Datamax GPS send protocol (NMEA + GPFID)
 * As seen in the MA100-1010 router
 *
 * It sends its identity after the GPRMC sentence, and with the type
 * GPFID.
 */
public class MaxonProtocolDecoder extends BaseProtocolDecoder {

    private Position position = null;

    public MaxonProtocolDecoder(MaxonProtocol protocol) {
        super(protocol);
    }

    private static final Pattern PATTERN = Pattern.compile(
            "\\$GPRMC," +
            "(\\d{2})(\\d{2})(\\d{2})\\.(\\d{2})," + // Time (HHMMSS.SSS)
            "([AV])," +                    // Validity
            "(\\d{2})(\\d{2}\\.\\d{5})," + // Latitude (DDMM.MMMMM)
            "([NS])," +
            "(\\d{3})(\\d{2}\\.\\d{5})," + // Longitude (DDDMM.MMMMM)
            "([EW])," +
            "(\\d+\\.\\d{3})?," +          // Speed
            "(\\d+\\.\\d{2})?," +          // Course
            "(\\d{2})(\\d{2})(\\d{2})" +   // Date (DDMMYY)
            ".+");                         // Other (Checksumm)

    private static final Pattern PATTERN_GPFID = Pattern.compile("\\$GPFID,(\\d+)$");

    @Override
    protected Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {

        String sentence = (String) msg;

        // Detect device ID
        // Parse message
        if (sentence.contains("$GPRMC")) {

            // Parse message
            Matcher parser = PATTERN.matcher(sentence);
            if (!parser.matches()) {
                return null;
            }

            // Create new position
            position = new Position();

            Integer index = 1;

            // Time
            Calendar time = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            time.clear();
            time.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parser.group(index++)));
            time.set(Calendar.MINUTE, Integer.parseInt(parser.group(index++)));
            time.set(Calendar.SECOND, Integer.parseInt(parser.group(index++)));
            index += 1; // Skip milliseconds

            // Validity
            position.setValid(parser.group(index++).compareTo("A") == 0);

            // Latitude
            Double latitude = Double.parseDouble(parser.group(index++));
            latitude += Double.parseDouble(parser.group(index++)) / 60;
            if (parser.group(index++).compareTo("S") == 0) latitude = -latitude;
            position.setLatitude(latitude);

            // Longitude
            Double longitude = Double.parseDouble(parser.group(index++));
            longitude += Double.parseDouble(parser.group(index++)) / 60;
            if (parser.group(index++).compareTo("W") == 0) longitude = -longitude;
            position.setLongitude(longitude);

            // Speed
            String speed = parser.group(index++);
            if (speed != null) {
                position.setSpeed(Double.parseDouble(speed));
            }

            // Course
            String course = parser.group(index++);
            if (course != null) {
                position.setCourse(Double.parseDouble(course));
            }

            // Date
            time.set(Calendar.DAY_OF_MONTH, Integer.parseInt(parser.group(index++)));
            time.set(Calendar.MONTH, Integer.parseInt(parser.group(index++)) - 1);
            time.set(Calendar.YEAR, 2000 + Integer.parseInt(parser.group(index++)));
            position.setTime(time.getTime());

        } else if (sentence.contains("$GPFID") && position != null) {
            Matcher parser = PATTERN_GPFID.matcher(sentence);

            if (parser.matches()) {
                if (!identify(parser.group(1), channel)) {
                    return null;
                }
                position.setDeviceId(getDeviceId());
                return position;
            }
        }

        return null;
    }

}
