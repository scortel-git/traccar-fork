/*
 * Copyright 2013 - 2014 Anton Tananaev (anton.tananaev@gmail.com)
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

public class MegastekProtocolDecoder extends BaseProtocolDecoder {

    public MegastekProtocolDecoder(MegastekProtocol protocol) {
        super(protocol);
    }

    private static final Pattern PATTERN_GPRMC = new PatternBuilder()
            .txt("$GPRMC,")
            .num("(dd)(dd)(dd).d+,")             // time
            .xpr("([AV]),")                      // validity
            .num("(d+)(dd.d+),([NS]),")          // latitude
            .num("(d+)(dd.d+),([EW]),")          // longitude
            .num("(d+.d+)?,")                    // speed
            .num("(d+.d+)?,")                    // course
            .num("(dd)(dd)(dd)")                 // date (ddmmyy)
            .any()                               // checksum
            .compile();

    private static final Pattern PATTERN_SIMPLE = new PatternBuilder()
            .xpr("[FL],")                        // flag
            .xpr("([^,]*),")                     // alarm
            .num("imei:(d+),")                   // imei
            .num("(d+/?d*)?,")                   // satellites
            .num("(d+.d+)?,")                    // altitude
            .num("Battery=(d+)%,,")              // battery
            .num("(d)?,")                        // charger
            .num("(d+)?,")                       // mcc
            .num("(d+)?,")                       // mnc
            .num("(xxxx,xxxx);")                 // location code
            .any()                               // checksum
            .compile();

    private static final Pattern PATTERN_ALTERNATIVE = new PatternBuilder()
            .num("(d+),")                        // mcc
            .num("(d+),")                        // mnc
            .num("(xxxx,xxxx),")                 // location code
            .num("(d+),")                        // gsm signal
            .num("(d+),")                        // battery
            .num("(d+),")                        // flags
            .num("(d+),")                        // inputs
            .num("(?:(d+),)?")                   // outputs
            .num("(d.?d*),")                     // adc 1
            .groupBegin()
            .num("(d.dd),")                      // adc 2
            .num("(d.dd),")                      // adc 3
            .groupEnd(true)
            .xpr("([^;]+);")                     // alarm
            .any()                               // checksum
            .compile();

    private boolean parseLocation(String location, Position position) {

        Parser parser = new Parser(PATTERN_GPRMC, location);
        if (!parser.matches()) {
            return false;
        }

        DateBuilder dateBuilder = new DateBuilder()
                .setTime(parser.nextInt(), parser.nextInt(), parser.nextInt());

        position.setValid(parser.next().equals("A"));
        position.setLatitude(parser.nextCoordinate());
        position.setLongitude(parser.nextCoordinate());
        position.setSpeed(parser.nextDouble());
        position.setCourse(parser.nextDouble());

        dateBuilder.setDateReverse(parser.nextInt(), parser.nextInt(), parser.nextInt());
        position.setTime(dateBuilder.getDate());

        return true;
    }

    private Position decodeOld(Channel channel, String sentence) {

        // Detect type
        boolean simple = sentence.charAt(3) == ',' || sentence.charAt(6) == ',';

        // Split message
        String id;
        String location;
        String status;
        if (simple) {

            int beginIndex = sentence.indexOf(',') + 1;
            int endIndex = sentence.indexOf(',', beginIndex);
            id = sentence.substring(beginIndex, endIndex);

            beginIndex = endIndex + 1;
            endIndex = sentence.indexOf('*', beginIndex);
            if (endIndex != -1) {
                endIndex += 3;
            } else {
                endIndex = sentence.length();
            }
            location = sentence.substring(beginIndex, endIndex);

            beginIndex = endIndex + 1;
            if (beginIndex > sentence.length()) {
                beginIndex = endIndex;
            }
            status = sentence.substring(beginIndex);

        } else {

            int beginIndex = 3;
            int endIndex = beginIndex + 16;
            id = sentence.substring(beginIndex, endIndex).trim();

            beginIndex = endIndex + 2;
            endIndex = sentence.indexOf('*', beginIndex) + 3;
            location = sentence.substring(beginIndex, endIndex);

            beginIndex = endIndex + 1;
            status = sentence.substring(beginIndex);

        }

        Position position = new Position();
        position.setProtocol(getProtocolName());
        if (!parseLocation(location, position)) {
            return null;
        }

        if (simple) {

            Parser parser = new Parser(PATTERN_SIMPLE, status);
            if (parser.matches()) {

                position.set(Event.KEY_ALARM, parser.next());

                if (!identify(parser.next(), channel, null, false) && !identify(id, channel)) {
                    return null;
                }
                position.setDeviceId(getDeviceId());

                position.set(Event.KEY_SATELLITES, parser.next());

                position.setAltitude(parser.nextDouble());

                position.set(Event.KEY_POWER, parser.nextDouble());

                String charger = parser.next();
                if (charger != null) {
                    position.set(Event.KEY_CHARGE, Integer.parseInt(charger) == 1);
                }

                position.set(Event.KEY_MCC, parser.next());
                position.set(Event.KEY_MNC, parser.next());
                position.set(Event.KEY_LAC, parser.next());

            } else {

                if (!identify(id, channel)) {
                    return null;
                }
                position.setDeviceId(getDeviceId());

            }

        } else {

            Parser parser = new Parser(PATTERN_ALTERNATIVE, status);
            if (parser.matches()) {

                if (!identify(id, channel)) {
                    return null;
                }
                position.setDeviceId(getDeviceId());

                position.set(Event.KEY_MCC, parser.next());
                position.set(Event.KEY_MNC, parser.next());
                position.set(Event.KEY_LAC, parser.next());
                position.set(Event.KEY_GSM, parser.next());

                position.set(Event.KEY_BATTERY, Double.parseDouble(parser.next()));

                position.set(Event.KEY_FLAGS, parser.next());
                position.set(Event.KEY_INPUT, parser.next());
                position.set(Event.KEY_OUTPUT, parser.next());
                position.set(Event.PREFIX_ADC + 1, parser.next());
                position.set(Event.PREFIX_ADC + 2, parser.next());
                position.set(Event.PREFIX_ADC + 3, parser.next());
                position.set(Event.KEY_ALARM, parser.next());

            }
        }

        return position;
    }

    private static final Pattern PATTERN_NEW = new PatternBuilder()
            .txt("$MGV")
            .num("ddd,")
            .num("(d+),")                        // imei
            .nxt(",")                            // name
            .xpr("([RS]),")
            .num("(dd)(dd)(dd),")                // date (ddmmyy)
            .num("(dd)(dd)(dd),")                // time
            .xpr("([AV]),")                      // validity
            .num("(d+)(dd.d+),([NS]),")          // latitude
            .num("(d+)(dd.d+),([EW]),")          // longitude
            .num("dd,")
            .num("(dd),")                        // satellites
            .num("dd,")
            .num("(d+.d+),")                     // hdop
            .num("(d+.d+),")                     // speed
            .num("(d+.d+),")                     // course
            .num("(d+.d+),")                     // altitude
            .num("(d+.d+),")                     // odometer
            .num("(d+),")                        // mcc
            .num("(d+),")                        // mnc
            .num("(xxxx,xxxx),")                 // cell
            .num("(d+)?,")                       // gsm
            .xpr("([01]+),")                     // input
            .xpr("([01]+),")                     // output
            .num("(d+),")                        // adc1
            .num("(d+),")                        // adc2
            .num("(d+),")                        // adc3
            .groupBegin()
            .num("(-?d+.?d*)")                   // temperature 1
            .or().txt(" ")
            .groupEnd(false).txt(",")
            .groupBegin()
            .num("(-?d+.?d*)")                   // temperature 2
            .or().txt(" ")
            .groupEnd(false).txt(",")
            .num("(d+)?,,")                      // rfid
            .num("(d+)?,")                       // battery
            .xpr("([^,]*);")                     // alert
            .any()
            .compile();

    private Position decodeNew(Channel channel, String sentence) {

        Parser parser = new Parser(PATTERN_NEW, sentence);
        if (!parser.matches()) {
            return null;
        }

        Position position = new Position();
        position.setProtocol(getProtocolName());

        if (!identify(parser.next(), channel)) {
            return null;
        }
        position.setDeviceId(getDeviceId());

        if (parser.next().equals("S")) {
            position.set(Event.KEY_ARCHIVE, true);
        }

        DateBuilder dateBuilder = new DateBuilder()
                .setDateReverse(parser.nextInt(), parser.nextInt(), parser.nextInt())
                .setTime(parser.nextInt(), parser.nextInt(), parser.nextInt());
        position.setTime(dateBuilder.getDate());

        position.setValid(parser.next().equals("A"));
        position.setLatitude(parser.nextCoordinate());
        position.setLongitude(parser.nextCoordinate());

        position.set(Event.KEY_SATELLITES, parser.nextInt());
        position.set(Event.KEY_HDOP, parser.nextDouble());

        position.setSpeed(parser.nextDouble());
        position.setCourse(parser.nextDouble());
        position.setAltitude(parser.nextDouble());

        position.set(Event.KEY_ODOMETER, parser.nextDouble());
        position.set(Event.KEY_MCC, parser.nextInt());
        position.set(Event.KEY_MNC, parser.nextInt());
        position.set(Event.KEY_CELL, parser.next());

        String gsm = parser.next();
        if (gsm != null) {
            position.set(Event.KEY_GSM, Integer.parseInt(gsm));
        }

        position.set(Event.KEY_INPUT, parser.nextInt(2));
        position.set(Event.KEY_OUTPUT, parser.nextInt(2));

        for (int i = 1; i <= 3; i++) {
            position.set(Event.PREFIX_ADC + i, parser.nextInt());
        }

        for (int i = 1; i <= 2; i++) {
            String adc = parser.next();
            if (adc != null) {
                position.set(Event.PREFIX_TEMP + i, Double.parseDouble(adc));
            }
        }

        position.set(Event.KEY_RFID, parser.next());

        String battery = parser.next();
        if (battery != null) {
            position.set(Event.KEY_BATTERY, Integer.parseInt(battery));
        }

        position.set(Event.KEY_ALARM, parser.next());

        return position;
    }

    @Override
    protected Object decode(
            Channel channel, SocketAddress remoteAddress, Object msg)
            throws Exception {

        String sentence = (String) msg;

        if (sentence.startsWith("$MG")) {
            return decodeNew(channel, sentence);
        } else {
            return decodeOld(channel, sentence);
        }
    }

}
