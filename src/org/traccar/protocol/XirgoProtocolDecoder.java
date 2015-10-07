/*
 * Copyright 2015 Anton Tananaev (anton.tananaev@gmail.com)
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

public class XirgoProtocolDecoder extends BaseProtocolDecoder {

    public XirgoProtocolDecoder(XirgoProtocol protocol) {
        super(protocol);
    }

    private static final Pattern PATTERN = Pattern.compile(
            "\\$\\$" +
            "(\\d+)," +                         // IMEI
            "(\\d+)," +                         // Event
            "(\\d{4})/(\\d{2})/(\\d{2})," +     // Date
            "(\\d{2}):(\\d{2}):(\\d{2})," +     // Time
            "(-?\\d+\\.?\\d*)," +               // Latitude
            "(-?\\d+\\.?\\d*)," +               // Longitude
            "(-?\\d+\\.?\\d*)," +               // Altitude
            "(\\d+\\.?\\d*)," +                 // Speed
            "(\\d+\\.?\\d*)," +                 // Course
            "(\\d+)," +                         // Satellites
            "(\\d+\\.?\\d*)," +                 // HDOP
            "(\\d+\\.\\d+)," +                  // Battery
            "(\\d+)," +                         // GSM
            "(\\d+\\.?\\d*)," +                 // Odometer
            "(\\d+)," +                         // GPS
            ".*");

    @Override
    protected Object decode(
            Channel channel, SocketAddress remoteAddress, Object msg)
            throws Exception {

        String sentence = (String) msg;

        // Parse message
        Matcher parser = PATTERN.matcher(sentence);
        if (!parser.matches()) {
            return null;
        }

        // Create new position
        Position position = new Position();
        position.setProtocol(getProtocolName());

        Integer index = 1;

        // Get device by IMEI
        if (!identify(parser.group(index++), channel, remoteAddress)) {
            return null;
        }
        position.setDeviceId(getDeviceId());

        position.set(Event.KEY_EVENT, parser.group(index++));

        // Date
        Calendar time = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        time.clear();
        time.set(Calendar.YEAR, Integer.parseInt(parser.group(index++)));
        time.set(Calendar.MONTH, Integer.parseInt(parser.group(index++)) - 1);
        time.set(Calendar.DAY_OF_MONTH, Integer.parseInt(parser.group(index++)));
        time.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parser.group(index++)));
        time.set(Calendar.MINUTE, Integer.parseInt(parser.group(index++)));
        time.set(Calendar.SECOND, Integer.parseInt(parser.group(index++)));
        position.setTime(time.getTime());

        // Location
        position.setLatitude(Double.parseDouble(parser.group(index++)));
        position.setLongitude(Double.parseDouble(parser.group(index++)));
        position.setAltitude(Double.parseDouble(parser.group(index++)));
        position.setSpeed(UnitsConverter.knotsFromMph(Double.parseDouble(parser.group(index++))));
        position.setCourse(Double.parseDouble(parser.group(index++)));

        // Additional data
        position.set(Event.KEY_SATELLITES, parser.group(index++));
        position.set(Event.KEY_HDOP, parser.group(index++));
        position.set(Event.KEY_BATTERY, parser.group(index++));
        position.set(Event.KEY_GSM, parser.group(index++));
        position.set(Event.KEY_ODOMETER, parser.group(index++));

        // Validity
        position.setValid(Integer.parseInt(parser.group(index++)) == 1);

        return position;
    }

}
