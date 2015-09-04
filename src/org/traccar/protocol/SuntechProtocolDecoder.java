/*
 * Copyright 2013 - 2015 Anton Tananaev (anton.tananaev@gmail.com)
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
import org.traccar.helper.UnitsConverter;
import org.traccar.model.Event;
import org.traccar.model.Position;

public class SuntechProtocolDecoder extends BaseProtocolDecoder {

    public SuntechProtocolDecoder(SuntechProtocol protocol) {
        super(protocol);
    }

    private static final Pattern pattern = Pattern.compile(
            "S.\\d{3}(?:\\w{3})?;" +       // Header
            "(?:([^;]+);)?" +              // Type
            "(\\d{6,});" +                 // Device ID
            "(?:\\d+;)?" +
            "(\\d+);" +                    // Version
            "(\\d{4})(\\d{2})(\\d{2});" +  // Date (YYYYMMDD)
            "(\\d{2}):(\\d{2}):(\\d{2});" + // Time (HH:MM:SS)
            "(?:(\\p{XDigit}+);)?" +       // Cell
            "([-\\+]\\d{2}\\.\\d+);" +     // Latitude
            "([-\\+]\\d{3}\\.\\d+);" +     // Longitude
            "(\\d{3}\\.\\d{3});" +         // Speed
            "(\\d{3}\\.\\d{2});" +         // Course
            "(?:\\d+;)?" +
            "(\\d+\\.\\d+)?" +             // Battery
            ".*");                         // Full format

    @Override
    protected Object decode(
            Channel channel, SocketAddress remoteAddress, Object msg)
            throws Exception {

        String sentence = (String) msg;

        // Parse message
        Matcher parser = pattern.matcher(sentence);
        if (!parser.matches()) {
            return null;
        }

        // Create new position
        Position position = new Position();
        position.setProtocol(getProtocolName());
        int index = 1;
        
        String type = parser.group(index++);
        if (type != null && (type.equals("Alert") || type.equals("Emergency"))) {
            position.set(Event.KEY_ALARM, true);
        }

        // Identifier
        if (!identify(parser.group(index++), channel)) {
            return null;
        }
        position.setDeviceId(getDeviceId());
        
        // Version
        position.set(Event.KEY_VERSION, parser.group(index++));

        // Date and Time
        Calendar time = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        time.clear();
        time.set(Calendar.YEAR, Integer.valueOf(parser.group(index++)));
        time.set(Calendar.MONTH, Integer.valueOf(parser.group(index++)) - 1);
        time.set(Calendar.DAY_OF_MONTH, Integer.valueOf(parser.group(index++)));
        time.set(Calendar.HOUR_OF_DAY, Integer.valueOf(parser.group(index++)));
        time.set(Calendar.MINUTE, Integer.valueOf(parser.group(index++)));
        time.set(Calendar.SECOND, Integer.valueOf(parser.group(index++)));
        position.setTime(time.getTime());
        
        // Cell
        position.set(Event.KEY_CELL, parser.group(index++));

        // Coordinates
        position.setLatitude(Double.valueOf(parser.group(index++)));
        position.setLongitude(Double.valueOf(parser.group(index++)));
        position.setValid(true); // wrong?

        // Speed
        position.setSpeed(UnitsConverter.knotsFromKph(Double.valueOf(parser.group(index++))));

        // Course
        position.setCourse(Double.valueOf(parser.group(index++)));
        
        // Battery
        position.set(Event.KEY_BATTERY, parser.group(index++));

        return position;
    }

}
