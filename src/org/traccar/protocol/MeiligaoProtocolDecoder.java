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

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.util.regex.Pattern;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.traccar.BaseProtocolDecoder;
import org.traccar.Context;
import org.traccar.helper.Checksum;
import org.traccar.helper.DateBuilder;
import org.traccar.helper.Parser;
import org.traccar.model.Event;
import org.traccar.model.Position;

public class MeiligaoProtocolDecoder extends BaseProtocolDecoder {

    public MeiligaoProtocolDecoder(MeiligaoProtocol protocol) {
        super(protocol);
    }

    private static final Pattern PATTERN = Pattern.compile(
            "(\\d{2})(\\d{2})(\\d{2})\\.?(\\d+)?," + // Time (HHMMSS.SSS)
            "([AV])," +                         // Validity
            "(\\d+)(\\d{2}\\.\\d+)," +          // Latitude (DDMM.MMMM)
            "([NS])," +
            "(\\d+)(\\d{2}\\.\\d+)," +          // Longitude (DDDMM.MMMM)
            "([EW])," +
            "(\\d+\\.?\\d*)?," +                // Speed
            "(\\d+\\.?\\d*)?," +                // Course
            "(\\d{2})(\\d{2})(\\d{2})" +        // Date (DDMMYY)
            "[^\\|]*" +
            "(?:\\|(\\d+\\.\\d+)?" +            // HDOP
            "\\|(-?\\d+\\.?\\d*)?" +            // Altitude
            "\\|(\\p{XDigit}{4})?" +            // State
            "(?:\\|(\\p{XDigit}{4}),(\\p{XDigit}{4})" + // ADC
            "(?:,(\\p{XDigit}{4}),(\\p{XDigit}{4}),(\\p{XDigit}{4}),(\\p{XDigit}{4}),(\\p{XDigit}{4}),(\\p{XDigit}{4}))?" +
            "(?:\\|" +
            "(?:(\\p{XDigit}{16})" +            // Cell
            "\\|(\\p{XDigit}{2})" +             // GSM
            "\\|(\\p{XDigit}{8})|" +            // Odometer
            "(\\p{XDigit}{9})" +                // Odometer
            "(?:\\|(\\p{XDigit}{5,}))?)?)?)?)?" + // RFID
            ".*");

    private static final Pattern PATTERN_RFID = Pattern.compile(
            "\\|(\\d{2})(\\d{2})(\\d{2})," +    // Time (HHMMSS)
            "(\\d{2})(\\d{2})(\\d{2})," +       // Date (DDMMYY)
            "(\\d+)(\\d{2}\\.\\d+)," +          // Latitude (DDMM.MMMM)
            "([NS])," +
            "(\\d+)(\\d{2}\\.\\d+)," +          // Longitude (DDDMM.MMMM)
            "([EW])");

    public static final int MSG_HEARTBEAT = 0x0001;
    public static final int MSG_SERVER = 0x0002;
    public static final int MSG_LOGIN = 0x5000;
    public static final int MSG_LOGIN_RESPONSE = 0x4000;

    public static final int MSG_POSITION = 0x9955;
    public static final int MSG_POSITION_LOGGED = 0x9016;
    public static final int MSG_ALARM = 0x9999;

    public static final int MSG_RFID = 0x9966;

    private boolean identify(ChannelBuffer buf, Channel channel) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 7; i++) {
            int b = buf.readUnsignedByte();

            // First digit
            int d1 = (b & 0xf0) >> 4;
            if (d1 == 0xf) {
                break;
            }
            builder.append(d1);

            // Second digit
            int d2 = b & 0x0f;
            if (d2 == 0xf) {
                break;
            }
            builder.append(d2);
        }

        String id = builder.toString();

        // Try to recreate full IMEI number
        // Sometimes first digit is cut, so this won't work
        if (id.length() == 14 && identify(id + Checksum.luhn(Long.parseLong(id)), channel, null, false)) {
            return true;
        }

        return identify(id, channel);
    }

    private static void sendResponse(
            Channel channel, ChannelBuffer id, int type, ChannelBuffer msg) {

        if (channel != null) {
            ChannelBuffer buf = ChannelBuffers.buffer(
                    2 + 2 + id.readableBytes() + 2 + msg.readableBytes() + 2 + 2);

            buf.writeByte('@');
            buf.writeByte('@');
            buf.writeShort(buf.capacity());
            buf.writeBytes(id);
            buf.writeShort(type);
            buf.writeBytes(msg);
            buf.writeShort(Checksum.crc16(Checksum.CRC16_CCITT_FALSE, buf.toByteBuffer()));
            buf.writeByte('\r');
            buf.writeByte('\n');

            channel.write(buf);
        }
    }

    private String getMeiligaoServer(Channel channel) {
        String server = Context.getConfig().getString(getProtocolName() + ".server");
        if (server == null) {
            InetSocketAddress address = (InetSocketAddress) channel.getLocalAddress();
            server = address.getAddress().getHostAddress() + ":" + address.getPort();
        }
        return server;
    }

    @Override
    protected Object decode(
            Channel channel, SocketAddress remoteAddress, Object msg)
            throws Exception {

        ChannelBuffer buf = (ChannelBuffer) msg;
        buf.skipBytes(2); // header
        buf.readShort(); // length
        ChannelBuffer id = buf.readBytes(7);
        int command = buf.readUnsignedShort();
        ChannelBuffer response;

        switch (command) {
            case MSG_LOGIN:
                if (channel != null) {
                    response = ChannelBuffers.wrappedBuffer(new byte[] {0x01});
                    sendResponse(channel, id, MSG_LOGIN_RESPONSE, response);
                }
                return null;
            case MSG_HEARTBEAT:
                if (channel != null) {
                    response = ChannelBuffers.wrappedBuffer(new byte[] {0x01});
                    sendResponse(channel, id, MSG_HEARTBEAT, response);
                }
                return null;
            case MSG_SERVER:
                if (channel != null) {
                    response = ChannelBuffers.copiedBuffer(
                            getMeiligaoServer(channel), Charset.defaultCharset());
                    sendResponse(channel, id, MSG_SERVER, response);
                }
                return null;
            case MSG_POSITION:
            case MSG_POSITION_LOGGED:
            case MSG_ALARM:
            case MSG_RFID:
                break;
            default:
                return null;
        }

        // Create new position
        Position position = new Position();
        position.setProtocol(getProtocolName());

        // Custom data
        if (command == MSG_ALARM) {
            position.set(Event.KEY_ALARM, buf.readUnsignedByte());
        } else if (command == MSG_POSITION_LOGGED) {
            buf.skipBytes(6);
        }

        // Get device by id
        if (!identify(id, channel)) {
            return null;
        }
        position.setDeviceId(getDeviceId());

        // RFID
        if (command == MSG_RFID) {
            for (int i = 0; i < 15; i++) {
                long rfid = buf.readUnsignedInt();
                if (rfid != 0) {
                    String card = String.format("%010d", rfid);
                    position.set("card" + (i + 1), card);
                    position.set(Event.KEY_RFID, card);
                }
            }
        }

        Pattern pattern;
        if (command == MSG_RFID) {
            pattern = PATTERN_RFID;
        } else {
            pattern = PATTERN;
        }

        Parser parser = new Parser(pattern, buf.toString(buf.readerIndex(), buf.readableBytes() - 4, Charset.defaultCharset()));
        if (!parser.matches()) {
            return null;
        }

        if (command == MSG_RFID) {

            DateBuilder dateBuilder = new DateBuilder()
                    .setTime(parser.nextInt(), parser.nextInt(), parser.nextInt())
                    .setDateReverse(parser.nextInt(), parser.nextInt(), parser.nextInt());
            position.setTime(dateBuilder.getDate());

            position.setLatitude(parser.nextCoordinate());
            position.setLongitude(parser.nextCoordinate());

        } else {

            DateBuilder dateBuilder = new DateBuilder()
                    .setTime(parser.nextInt(), parser.nextInt(), parser.nextInt());
            if (parser.hasNext()) {
                dateBuilder.setMillis(parser.nextInt());
            }

            position.setValid(parser.next().equals("A"));
            position.setLatitude(parser.nextCoordinate());
            position.setLongitude(parser.nextCoordinate());

            if (parser.hasNext()) {
                position.setSpeed(parser.nextDouble());
            }

            if (parser.hasNext()) {
                position.setCourse(parser.nextDouble());
            }

            dateBuilder.setDateReverse(parser.nextInt(), parser.nextInt(), parser.nextInt());
            position.setTime(dateBuilder.getDate());

            position.set(Event.KEY_HDOP, parser.next());

            if (parser.hasNext()) {
                position.setAltitude(parser.nextDouble());
            }

            position.set(Event.KEY_STATUS, parser.next());

            for (int i = 1; i <= 8; i++) {
                if (parser.hasNext()) {
                    position.set(Event.PREFIX_ADC + i, parser.nextInt(16));
                }
            }

            position.set(Event.KEY_CELL, parser.next());

            if (parser.hasNext()) {
                position.set(Event.KEY_GSM, parser.nextInt(16));
            }

            if (parser.hasNext()) {
                position.set(Event.KEY_ODOMETER, parser.nextInt(16));
            } else if (parser.hasNext()) {
                position.set(Event.KEY_ODOMETER, parser.nextInt(16));
            }

            if (parser.hasNext()) {
                position.set(Event.KEY_RFID, parser.nextInt(16));
            }

        }

        return position;
    }

}
