/*
 * Copyright 2012 - 2014 Anton Tananaev (anton.tananaev@gmail.com)
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
import org.traccar.Context;
import org.traccar.helper.UnitsConverter;
import org.traccar.model.Event;
import org.traccar.model.Position;

public class Tk103ProtocolDecoder extends BaseProtocolDecoder {

    public Tk103ProtocolDecoder(Tk103Protocol protocol) {
        super(protocol);
    }

    private static final Pattern pattern = Pattern.compile(
            "(\\d+)(,)?" +                 // Device ID
            ".{4},?" +                     // Command
            "\\d*" +                       // IMEI (?)
            "(\\d{2})(\\d{2})(\\d{2}),?" + // Date (YYMMDD)
            "([AV]),?" +                   // Validity
            "(\\d{2})(\\d{2}\\.\\d+)" +    // Latitude (DDMM.MMMM)
            "([NS]),?" +
            "(\\d{3})(\\d{2}\\.\\d+)" +    // Longitude (DDDMM.MMMM)
            "([EW]),?" +
            "(\\d+\\.\\d)(?:\\d*,)?" +     // Speed
            "(\\d{2})(\\d{2})(\\d{2}),?" + // Time (HHMMSS)
            "(\\d+\\.?\\d+),?" +           // Course
            "([0-9a-fA-F]{8})?,?" +        // State
            "(?:L([0-9a-fA-F]+))?\\)?");   // Odometer

    @Override
    protected Object decode(
            ChannelHandlerContext ctx, Channel channel, Object msg)
            throws Exception {

        String sentence = (String) msg;

        // Find message start
        int beginIndex = sentence.indexOf('(');
        if (beginIndex != -1) {
            sentence = sentence.substring(beginIndex + 1);
        }
        
        // Send response
        if (channel != null) {
            String id = sentence.substring(0, 12);
            String type = sentence.substring(12, 16);
            if (type.equals("BP00")) {
                String content = sentence.substring(sentence.length() - 3);
                channel.write("(" + id + "AP01" + content + ")");
            } else if (type.equals("BP05")) {
                channel.write("(" + id + "AP05)");
            }
        }

        // Parse message
        Matcher parser = pattern.matcher(sentence);
        if (!parser.matches()) {
            return null;
        }

        // Create new position
        Position position = new Position();
        position.setProtocol(getProtocolName());
        Integer index = 1;

        // Get device by IMEI
        if (!identify(parser.group(index++), channel)) {
            return null;
        }
        position.setDeviceId(getDeviceId());

        // Date
        Calendar time = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        time.clear();
        if (parser.group(index++) == null) {
            time.set(Calendar.YEAR, 2000 + Integer.valueOf(parser.group(index++)));
            time.set(Calendar.MONTH, Integer.valueOf(parser.group(index++)) - 1);
            time.set(Calendar.DAY_OF_MONTH, Integer.valueOf(parser.group(index++)));
        } else {
            time.set(Calendar.DAY_OF_MONTH, Integer.valueOf(parser.group(index++)));
            time.set(Calendar.MONTH, Integer.valueOf(parser.group(index++)) - 1);
            time.set(Calendar.YEAR, 2000 + Integer.valueOf(parser.group(index++)));
        }

        // Validity
        position.setValid(parser.group(index++).compareTo("A") == 0);

        // Latitude
        Double latitude = Double.valueOf(parser.group(index++));
        latitude += Double.valueOf(parser.group(index++)) / 60;
        if (parser.group(index++).compareTo("S") == 0) latitude = -latitude;
        position.setLatitude(latitude);

        // Longitude
        Double longitude = Double.valueOf(parser.group(index++));
        longitude += Double.valueOf(parser.group(index++)) / 60;
        if (parser.group(index++).compareTo("W") == 0) longitude = -longitude;
        position.setLongitude(longitude);

        // Speed
        if (Boolean.valueOf(Context.getProps().getProperty(getProtocolName() + ".mph"))) {
            position.setSpeed(UnitsConverter.knotsFromMph(Double.valueOf(parser.group(index++))));
        } else {
            position.setSpeed(UnitsConverter.knotsFromKph(Double.valueOf(parser.group(index++))));
        }

        // Time
        time.set(Calendar.HOUR_OF_DAY, Integer.valueOf(parser.group(index++)));
        time.set(Calendar.MINUTE, Integer.valueOf(parser.group(index++)));
        time.set(Calendar.SECOND, Integer.valueOf(parser.group(index++)));
        position.setTime(time.getTime());

        // Course
        position.setCourse(Double.valueOf(parser.group(index++)));
        
        // State
        position.set(Event.KEY_STATUS, parser.group(index++));

        // Odometer
        String odometer = parser.group(index++);
        if (odometer != null) {
            position.set(Event.KEY_ODOMETER, Long.parseLong(odometer, 16));
        }
        return position;
    }

}
