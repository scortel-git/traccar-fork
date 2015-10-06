/*
 * Copyright 2012 - 2015 Anton Tananaev (anton.tananaev@gmail.com)
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
import org.traccar.helper.PatternBuilder;
import org.traccar.model.Event;
import org.traccar.model.Position;

public class XexunProtocolDecoder extends BaseProtocolDecoder {

    private final boolean full;

    public XexunProtocolDecoder(XexunProtocol protocol, boolean full) {
        super(protocol);
        this.full = full;
    }

    private static final Pattern patternBasic = new PatternBuilder()
            .xpr("G[PN]RMC,")
            .num("(dd)(dd)(dd).(d+),")           // time
            .xpr("([AV]),")                      // validity
            .num("(d+)(dd.d+),([NS]),")          // latitude
            .num("(d+)(dd.d+),([EW])?,")         // longitude
            .num("(d+.?d*),")                    // speed
            .num("(d+.?d*)?,")                   // course
            .num("(dd)(dd)(dd),")                // date
            .nxt("*")
            .num("xx,")                          // checksum
            .xpr("([FL]),")                      // signal
            .opx("([^,]*),")                     // alarm
            .any()
            .num("imei:(d+),")                   // imei
            .compile();

    private static final Pattern patternFull = new PatternBuilder()
            .any()
            .num("(d+),")                        // serial
            .xpr("([^,]+)?,")                    // phone number
            .xpr(patternBasic.pattern())
            .num("(d+),")                        // satellites
            .num("(-?d+.d+)?,")                  // altitude
            .num("[FL]:(d+.d+)V")                // power
            .any()
            .compile();

    @Override
    protected Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {

        Pattern pattern = full ? patternFull : patternBasic;
        Matcher parser = pattern.matcher((String) msg);
        if (!parser.matches()) {
            return null;
        }

        Position position = new Position();
        position.setProtocol(getProtocolName());

        Integer index = 1;

        if (full) {
            position.set("serial", parser.group(index++));
            position.set("number", parser.group(index++));
        }

        // Time
        Calendar time = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        time.clear();
        time.set(Calendar.HOUR_OF_DAY, Integer.valueOf(parser.group(index++)));
        time.set(Calendar.MINUTE, Integer.valueOf(parser.group(index++)));
        time.set(Calendar.SECOND, Integer.valueOf(parser.group(index++)));
        time.set(Calendar.MILLISECOND, Integer.valueOf(parser.group(index++)));

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
        String hemisphere = parser.group(index++);
        if (hemisphere != null) {
            if (hemisphere.compareTo("W") == 0) longitude = -longitude;
        }
        position.setLongitude(longitude);

        // Speed
        position.setSpeed(Double.valueOf(parser.group(index++)));

        // Course
        String course = parser.group(index++);
        if (course != null) {
            position.setCourse(Double.valueOf(course));
        }

        // Date
        time.set(Calendar.DAY_OF_MONTH, Integer.valueOf(parser.group(index++)));
        time.set(Calendar.MONTH, Integer.valueOf(parser.group(index++)) - 1);
        time.set(Calendar.YEAR, 2000 + Integer.valueOf(parser.group(index++)));
        position.setTime(time.getTime());

        // Signal
        position.set("signal", parser.group(index++));

        // Alarm
        position.set(Event.KEY_ALARM, parser.group(index++));

        // Get device by IMEI
        if (!identify(parser.group(index++), channel)) {
            return null;
        }
        position.setDeviceId(getDeviceId());

        if (full) {

            // Satellites
            position.set(Event.KEY_SATELLITES, parser.group(index++).replaceFirst("^0*(?![\\.$])", ""));

            // Altitude
            String altitude = parser.group(index++);
            if (altitude != null) {
                position.setAltitude(Double.valueOf(altitude));
            }

            // Power
            position.set(Event.KEY_POWER, Double.valueOf(parser.group(index++)));
        }

        return position;
    }

}
