/*
 * Copyright 2014 - 2016 Anton Tananaev (anton@traccar.org)
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

import org.jboss.netty.channel.Channel;
import org.traccar.BaseProtocolDecoder;
import org.traccar.DeviceSession;
import org.traccar.helper.BitUtil;
import org.traccar.helper.DateBuilder;
import org.traccar.helper.Parser;
import org.traccar.helper.PatternBuilder;
import org.traccar.helper.UnitsConverter;
import org.traccar.model.Position;

import java.net.SocketAddress;
import java.util.regex.Pattern;

public class MiniFinderProtocolDecoder extends BaseProtocolDecoder {

    public MiniFinderProtocolDecoder(MiniFinderProtocol protocol) {
        super(protocol);
    }

    // The !A record is not in the documentation for MiniFinder or EV-07S.
    // Eview, the manufacturer, indicated that resellers could implement new record types.
    // Using the data in MiniFinderProtocolDecoderTest.java as a guide
    private static final Pattern PATTERN_A = new PatternBuilder()
            .expression("!A,")
            .number("(d+)/(d+)/(d+),")           // date
            .number("(d+):(d+):(d+),")           // time
            .number("(-?d+.d+),")                // latitude
            .number("(-?d+.d+),")                // longitude
            .number("(d+.?d*),")                 // unknown
            .number("(d+.?d*),")                 // unknown
            .number("(d+.?d*)")                  // unknown
            .compile();

    // The !C record is not in the documentation for MiniFinder or EV-07S
    // Eview, the manufacturer, indicated that resellers could implement new record types.
    // Using the data in MiniFinderProtocolDecoderTest.java as a guide
    private static final Pattern PATTERN_C = new PatternBuilder()
            .expression("!C,")
            .number("(d+)/(d+)/(d+),")           // date
            .number("(d+):(d+):(d+),")           // time
            .number("(-?d+.d+),")                // latitude
            .number("(-?d+.d+),")                // longitude
            .number("(d+.?d*),")                 // speed (km/h)
            .number("(d+.?d*),")                 // course
            .number("(x+),")                     // flags
            .number("(-?d+.d+),")                // altitude (meters)
            .number("(d+),")                     // battery (percentage)
            .number("(d+.?d*),")                 // unknown
            .number("(d+.?d*),")                 // unknown
            .number("(d+.?d*)")                  // unknown
            .compile();

    // The !B (buffered data) records are the same as !D (live data) records.
    // This was confirmed with the manufacturer, Eview.
    private static final Pattern PATTERN_BD = new PatternBuilder()
            .expression("![BD],")
            .number("(d+)/(d+)/(d+),")           // date
            .number("(d+):(d+):(d+),")           // time
            .number("(-?d+.d+),")                // latitude
            .number("(-?d+.d+),")                // longitude
            .number("(d+.?d*),")                 // speed (km/h)
            .number("(d+.?d*),")                 // course
            .number("(x+),")                     // flags
            .number("(-?d+.d+),")                // altitude (meters)
            .number("(d+),")                     // battery (percentage)
            .number("(d+),")                     // satellites in use
            .number("(d+),")                     // satellites in view
            .number("(d+.?d*)")                  // HDOP, confirmed with Eview manufacturer
            .compile();

    private void decodeFlags(Position position, int flags) {

        position.setValid(BitUtil.check(flags, 0));

        if (BitUtil.check(flags, 2)) {
            position.set(Position.KEY_ALARM, Position.ALARM_FAULT);
        }
        if (BitUtil.check(flags, 6)) {
            position.set(Position.KEY_ALARM, Position.ALARM_SOS);
        }
        if (BitUtil.check(flags, 7)) {
            position.set(Position.KEY_ALARM, Position.ALARM_OVERSPEED);
        }
        if (BitUtil.check(flags, 8)) {
            position.set(Position.KEY_ALARM, Position.ALARM_FALL_DOWN);
        }
        if (BitUtil.check(flags, 9) || BitUtil.check(flags, 10) || BitUtil.check(flags, 11)) {
            position.set(Position.KEY_ALARM, Position.ALARM_GEOFENCE);
        }
        if (BitUtil.check(flags, 12)) {
            position.set(Position.KEY_ALARM, Position.ALARM_LOW_BATTERY);
        }
        if (BitUtil.check(flags, 15) || BitUtil.check(flags, 14)) {
            position.set(Position.KEY_ALARM, Position.ALARM_MOVEMENT);
        }

        position.set(Position.KEY_RSSI, BitUtil.between(flags, 16, 20));
        position.set(Position.KEY_CHARGE, BitUtil.check(flags, 22));
    }

    @Override
    protected Object decode(
            Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {

        String sentence = (String) msg;

        if (sentence.startsWith("!1,")) {

            getDeviceSession(channel, remoteAddress, sentence.substring(3, sentence.length()));

            return null;
        }

        DeviceSession deviceSession = getDeviceSession(channel, remoteAddress);
        if (deviceSession == null) {
            return null;
        }

        if (sentence.startsWith("!B,") || sentence.startsWith("!D,")) {
            Parser parser = new Parser(PATTERN_BD, sentence);
            if (!parser.matches()) {
                return null;
            }

            Position position = new Position();
            position.setProtocol(getProtocolName());
            position.setDeviceId(deviceSession.getDeviceId());

            DateBuilder dateBuilder = new DateBuilder()
                    .setDateReverse(parser.nextInt(), parser.nextInt(), parser.nextInt())
                    .setTime(parser.nextInt(), parser.nextInt(), parser.nextInt());
            position.setTime(dateBuilder.getDate());

            position.setLatitude(parser.nextDouble());
            position.setLongitude(parser.nextDouble());
            position.setSpeed(UnitsConverter.knotsFromKph(parser.nextDouble()));

            position.setCourse(parser.nextDouble());
            if (position.getCourse() > 360) {
                position.setCourse(0);
            }

            decodeFlags(position, parser.nextInt(16));

            position.setAltitude(parser.nextDouble());

            position.set(Position.KEY_BATTERY, parser.nextInt());
            position.set(Position.KEY_SATELLITES, parser.nextInt());
            position.set(Position.KEY_SATELLITES_VISIBLE, parser.nextInt());
            position.set(Position.KEY_HDOP, parser.nextDouble());

            position.set(Position.KEY_TYPE, sentence.substring(1, 2));

            return position;
        }

        if (sentence.startsWith("!C,")) {
            Parser parser = new Parser(PATTERN_C, sentence);
            if (!parser.matches()) {
                return null;
            }

            Position position = new Position();
            position.setProtocol(getProtocolName());
            position.setDeviceId(deviceSession.getDeviceId());

            DateBuilder dateBuilder = new DateBuilder()
                    .setDateReverse(parser.nextInt(), parser.nextInt(), parser.nextInt())
                    .setTime(parser.nextInt(), parser.nextInt(), parser.nextInt());
            position.setTime(dateBuilder.getDate());

            position.setLatitude(parser.nextDouble());
            position.setLongitude(parser.nextDouble());
            position.setSpeed(UnitsConverter.knotsFromKph(parser.nextDouble()));

            position.setCourse(parser.nextDouble());
            if (position.getCourse() > 360) {
                position.setCourse(0);
            }

            decodeFlags(position, parser.nextInt(16));

            position.setAltitude(parser.nextDouble());

            position.set(Position.KEY_BATTERY, parser.nextInt());

            // Ignoring last three fields, unknown
            position.set("unknown", parser.next() + "," + parser.next() + "," + parser.next());

            position.set(Position.KEY_TYPE, sentence.substring(1, 2));

            return position;
        }

        if (sentence.startsWith("!A,")) {
            Parser parser = new Parser(PATTERN_A, sentence);
            if (!parser.matches()) {
                return null;
            }

            Position position = new Position();
            position.setProtocol(getProtocolName());
            position.setDeviceId(deviceSession.getDeviceId());

            DateBuilder dateBuilder = new DateBuilder()
                    .setDateReverse(parser.nextInt(), parser.nextInt(), parser.nextInt())
                    .setTime(parser.nextInt(), parser.nextInt(), parser.nextInt());
            position.setTime(dateBuilder.getDate());

            position.setLatitude(parser.nextDouble());
            position.setLongitude(parser.nextDouble());

            // Ignoring last three fields, unknown
            position.set("unknown", parser.next() + "," + parser.next() + "," + parser.next());

            return position;
        }

        // If we get here, we didn't understand the message

        return null;
    }
}
