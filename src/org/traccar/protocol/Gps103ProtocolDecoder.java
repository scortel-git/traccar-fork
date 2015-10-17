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
import java.util.regex.Pattern;
import org.jboss.netty.channel.Channel;
import org.traccar.BaseProtocolDecoder;
import org.traccar.helper.DateBuilder;
import org.traccar.helper.Parser;
import org.traccar.helper.PatternBuilder;
import org.traccar.model.Event;
import org.traccar.model.Position;

public class Gps103ProtocolDecoder extends BaseProtocolDecoder {

    public Gps103ProtocolDecoder(Gps103Protocol protocol) {
        super(protocol);
    }

    private static final Pattern PATTERN = new PatternBuilder()
            .txt("imei:")
            .num("(d+),")                        // imei
            .xpr("([^,]+),")                     // alarm
            .num("(dd)/?(dd)/?(dd) ?")           // local date
            .num("(dd):?(dd)(?:dd)?,")           // local time
            .nxt(",")
            .xpr("[FL],")                        // full / low
            .groupBegin()
            .num("(dd)(dd)(dd).(d+)")            // time utc (hhmmss.sss)
            .or()
            .opn("d{1,5}.d+")
            .groupEnd(false)
            .txt(",")
            .xpr("([AV]),")                      // validity
            .opx("([NS]),")
            .num("(d+)(dd.d+),")                 // latitude (ddmm.mmmm)
            .opx("([NS]),")
            .opx("([EW]),")
            .num("(d+)(dd.d+),")                 // longitude (dddmm.mmmm)
            .opx("([EW])?,")
            .num("(d+.?d*)?,?")                  // speed
            .num("(d+.?d*)?,?")                  // course
            .num("(d+.?d*)?,?")                  // altitude
            .xpr("([^,;]+)?,?")
            .xpr("([^,;]+)?,?")
            .xpr("([^,;]+)?,?")
            .xpr("([^,;]+)?,?")
            .xpr("([^,;]+)?,?")
            .any()
            .compile();

    private static final Pattern PATTERN_HANDSHAKE = new PatternBuilder()
            .num("##,imei:(d+),A")
            .compile();

    @Override
    protected Object decode(
            Channel channel, SocketAddress remoteAddress, Object msg)
            throws Exception {

        String sentence = (String) msg;

        // Send response #1
        if (sentence.contains("##")) {
            if (channel != null) {
                channel.write("LOAD", remoteAddress);
                Parser handshakeParser = new Parser(PATTERN_HANDSHAKE, sentence);
                if (handshakeParser.matches()) {
                    identify(handshakeParser.next(), channel);
                }
            }
            return null;
        }

        // Send response #2
        if (sentence.length() == 15 && Character.isDigit(sentence.charAt(0))) {
            if (channel != null) {
                channel.write("ON", remoteAddress);
            }
            return null;
        }

        Parser parser = new Parser(PATTERN, sentence);
        if (!parser.matches()) {
            return null;
        }

        Position position = new Position();
        position.setProtocol(getProtocolName());

        // Get device by IMEI
        String imei = parser.next();
        if (!identify(imei, channel, remoteAddress)) {
            return null;
        }
        position.setDeviceId(getDeviceId());

        String alarm = parser.next();
        position.set(Event.KEY_ALARM, alarm);
        if (channel != null && alarm.equals("help me")) {
            channel.write("**,imei:" + imei + ",E;", remoteAddress);
        }

        DateBuilder dateBuilder = new DateBuilder()
                .setDate(parser.nextInt(), parser.nextInt(), parser.nextInt());

        int localHours = parser.nextInt();
        int localMinutes = parser.nextInt();

        int utcHours = parser.nextInt();
        int utcMinutes = parser.nextInt();

        dateBuilder.setTime(localHours, localMinutes, parser.nextInt(), parser.nextInt());

        // Timezone calculation
        if (utcHours != 0 && utcMinutes != 0) {
            int deltaMinutes = (localHours - utcHours) * 60;
            deltaMinutes += localMinutes - utcMinutes;
            if (deltaMinutes <= -12 * 60) {
                deltaMinutes += 24 * 60;
            } else if (deltaMinutes > 12 * 60) {
                deltaMinutes -= 24 * 60;
            }
            dateBuilder.addMinute(-deltaMinutes);
        }
        position.setTime(dateBuilder.getDate());

        position.setValid(parser.next().equals("A"));
        position.setLatitude(parser.nextCoordinate(Parser.CoordinateFormat.HEM_DEG_MIN_HEM));
        position.setLongitude(parser.nextCoordinate(Parser.CoordinateFormat.HEM_DEG_MIN_HEM));
        position.setSpeed(parser.nextDouble());
        position.setCourse(parser.nextDouble());
        position.setAltitude(parser.nextDouble());

        for (int i = 1; i <= 5; i++) {
            position.set(Event.PREFIX_IO + i, parser.next());
        }

        return position;
    }

}
