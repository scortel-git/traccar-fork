package org.traccar.protocol;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.common.primitives.Shorts;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.BaseProtocolDecoder;
import org.traccar.Protocol;
import org.traccar.model.Position;
import org.traccar.session.ConnectionManager;
import org.traccar.session.DeviceSession;

import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class G1rusProtocolDecoder extends BaseProtocolDecoder {
    public G1rusProtocolDecoder(Protocol protocol) {
        super(protocol);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);

    /* Constants */
    private static final int G1RUS_HEAD_TAIL = 0xF8;

    private static final int G1RUS_TYPE_HEARTBEAT = 0;

    private static final int G1RUS_TYPE_BCD_MASK = 0b00111111;
    private static final int G1RUS_TYPE_REGULAR = 1;
    private static final int G1RUS_TYPE_SMS_FORWARD = 2;
    private static final int G1RUS_TYPE_SERIAL_PASS_THROUGH = 3;
    private static final int G1RUS_TYPE_MIXED = 4;

    private static final int G1RUS_TYPE_EVENT_MASK = 0b01000000;
    private static final int G1RUS_TYPE_NON_EVENT = 0;
    private static final int G1RUS_TYPE_EVENT = 1;

    private static final int G1RUS_TYPE_IMEI_MASK = 0b10000000;
    private static final int G1RUS_TYPE_IMEI_LONG = 0;
    private static final int G1RUS_TYPE_IMEI_SHORT = 1;

    private static final int G1RUS_DATA_SYS_MASK = 0b00000001;
    private static final int G1RUS_DATA_GPS_MASK = 0b00000010;
    private static final int G1RUS_DATA_GSM_MASK = 0b00000100;
    private static final int G1RUS_DATA_COT_MASK = 0b00001000;
    private static final int G1RUS_DATA_ADC_MASK = 0b00010000;
    private static final int G1RUS_DATA_DTT_MASK = 0b00100000;
    /* Reserved */
    private static final int G1RUS_DATA_ETD_MASK = 0b10000000;

    private static final int G1RUS_GPS_SIGN_MASK = 0b00000001;
    private static final int G1RUS_GPS_POS_MASK  = 0b00000010;
    private static final int G1RUS_GPS_SPD_MASK  = 0b00000100;
    private static final int G1RUS_GPS_AZTH_MASK = 0b00001000;
    private static final int G1RUS_GPS_ALT_MASK  = 0b00010000;
    private static final int G1RUS_GPS_HDOP_MASK = 0b00100000;
    private static final int G1RUS_GPS_VDOP_MASK = 0b01000000;
    private static final int G1RUS_GPS_STAT_MASK = 0b10000000;

    private static final int G1RUS_ESCAPE_CHAR = 0x1B;


    private short readUnsignedByteUnescaped(ByteBuf buf) {
        short first = buf.readUnsignedByte();
        if (first != G1RUS_ESCAPE_CHAR) {
            return first;
        } else { /* first == 0x1B */
            byte second = (byte) buf.readUnsignedByte();
            if (second == 0x00) {
                return first;
            } else { /* second == 0xE3 */
                return (short) 0xF8;
            }
        }
    }


    private void skipBytesUnescaped(ByteBuf buf, int howMany) {
        for (int i = 0; i < howMany; ++i) {
            readUnsignedByteUnescaped(buf);
        }
    }


    private void readBytesUnescaped(ByteBuf buf, byte[] to) {
        for (int i = 0; i < to.length; ++i) {
            to[i] = (byte) readUnsignedByteUnescaped(buf);
        }
    }

    private void readBytesUnescaped(ByteBuf buf, byte[] to, int dstIndex, int length) {
        for (int i = dstIndex; i < length; ++i) {
            to[i] = (byte) readUnsignedByteUnescaped(buf);
        }
    }


    private short readUnsignedShortUnescaped(ByteBuf buf) {
        byte[] shortBuf = new byte[2];
        readBytesUnescaped(buf, shortBuf);
        return Shorts.fromByteArray(shortBuf);
    }


    private int readIntUnescaped(ByteBuf buf) {
        byte[] intBuf = new byte[4];
        readBytesUnescaped(buf, intBuf);
        return Ints.fromByteArray(intBuf);
    }


    private void decodeSYSSub(ByteBuf buf) {
        LOGGER.info("<SYS>");

        skipBytesUnescaped(buf, 1); /* Total length */

        /* NOTE: assuming order:
         * Device name -> Firmware version -> Hardware version.
         * TODO: actually check it.
         */

        /* Device name */
        short devNameLen = readUnsignedByteUnescaped(buf);
        byte[] devName = new byte[devNameLen & 0xF];
        readBytesUnescaped(buf, devName);
        String devNameString = new String(devName);
        LOGGER.info("Device name: " + devNameString);

        /* Firmware version */
        short firmwareLen = readUnsignedByteUnescaped(buf);
        byte[] firmware = new byte[firmwareLen & 0xF];
        readBytesUnescaped(buf, firmware);
        String firmwareString = new String(firmware);
        LOGGER.info("Firmware version: " + firmwareString);

        /* Hardware version */
        short hardwareLen = readUnsignedByteUnescaped(buf);
        byte[] hardware = new byte[hardwareLen & 0xF];
        readBytesUnescaped(buf, hardware);
        String hardwareString = new String(hardware);
        LOGGER.info("Hardware version: " + hardwareString);

        LOGGER.info("</SYS>");
    }


    private void decodeGPSSub(ByteBuf buf, Position position) {
        LOGGER.info("<GPS>");

        skipBytesUnescaped(buf, 1); /* Total length */

        short subMask = readUnsignedShortUnescaped(buf);
        if ((subMask & G1RUS_GPS_SIGN_MASK) == G1RUS_GPS_SIGN_MASK) {
            skipBytesUnescaped(buf, 1);
        }
        if ((subMask & G1RUS_GPS_POS_MASK) == G1RUS_GPS_POS_MASK) {
            byte[] pos_buf = new byte[4];
            readBytesUnescaped(buf, pos_buf);
            position.setLatitude((float) Ints.fromByteArray(pos_buf) / 1000000);
            LOGGER.info("Latitude: " + position.getLatitude());

            readBytesUnescaped(buf, pos_buf);
            position.setLongitude((float) Ints.fromByteArray(pos_buf) / 1000000);
            LOGGER.info("Longitude: " + position.getLongitude());
        }
        if ((subMask & G1RUS_GPS_SPD_MASK) == G1RUS_GPS_SPD_MASK) {
            position.setSpeed(readUnsignedShortUnescaped(buf));
            LOGGER.info("Speed: " + position.getSpeed());
        }
        if ((subMask & G1RUS_GPS_AZTH_MASK) == G1RUS_GPS_AZTH_MASK) {
            position.setCourse(readUnsignedShortUnescaped(buf));
            LOGGER.info("Course: " + position.getCourse());
        }
        if ((subMask & G1RUS_GPS_ALT_MASK) == G1RUS_GPS_ALT_MASK) {
            position.setAltitude(readUnsignedShortUnescaped(buf));
            LOGGER.info("Altitude: " + position.getAltitude());
        }
        if ((subMask & G1RUS_GPS_HDOP_MASK) == G1RUS_GPS_HDOP_MASK) {
            skipBytesUnescaped(buf, 2);
        }
        if ((subMask & G1RUS_GPS_VDOP_MASK) == G1RUS_GPS_VDOP_MASK) {
            skipBytesUnescaped(buf, 2);
        }

        LOGGER.info("</GPS>");
    }


    private void decodeADCSub(ByteBuf buf, Position position) {

    }


    private Position decodeRegular(Channel channel, SocketAddress remoteAddress, ByteBuf buf, long imei, short packetType) {
        int timestamp_ = readIntUnescaped(buf);
        long timestamp = (946684800 + timestamp_) * 1000L; /* Convert received time to proper UNIX timestamp */
        LOGGER.info("Date and time: " + new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date(timestamp)));

        if ((packetType & G1RUS_TYPE_EVENT_MASK) != G1RUS_TYPE_NON_EVENT) {
            skipBytesUnescaped(buf, 1); /* Event ID */
        }

        DeviceSession deviceSession = null;
        Position position = null;

        short dataUploadingMask = readUnsignedShortUnescaped(buf);
        if ((dataUploadingMask & G1RUS_DATA_SYS_MASK) == G1RUS_DATA_SYS_MASK) {
            decodeSYSSub(buf);
        }
        if ((dataUploadingMask & G1RUS_DATA_GPS_MASK) == G1RUS_DATA_GPS_MASK) {
            deviceSession = getDeviceSession(channel, remoteAddress, String.valueOf(imei));
            if (deviceSession == null) {
                return null;
            }
            position = new Position(getProtocolName());
            position.setDeviceId(deviceSession.getDeviceId());
            position.setTime(new Date(timestamp));

            decodeGPSSub(buf, position);
        }
        if ((dataUploadingMask & G1RUS_DATA_GSM_MASK) == G1RUS_DATA_GSM_MASK) {
            skipBytesUnescaped(buf, readUnsignedByteUnescaped(buf));
        }
        if ((dataUploadingMask & G1RUS_DATA_COT_MASK) == G1RUS_DATA_COT_MASK) {
            skipBytesUnescaped(buf, readUnsignedByteUnescaped(buf));
        }
        if ((dataUploadingMask & G1RUS_DATA_ADC_MASK) == G1RUS_DATA_ADC_MASK) {
            /*if (deviceSession == null) {
                return null;
            }*/

            skipBytesUnescaped(buf, readUnsignedByteUnescaped(buf));
            // decodeADCSub(buf, position);
        }
        if ((dataUploadingMask & G1RUS_DATA_DTT_MASK) == G1RUS_DATA_DTT_MASK) {
            skipBytesUnescaped(buf, readUnsignedByteUnescaped(buf));
        }
        if ((dataUploadingMask & G1RUS_DATA_ETD_MASK) == G1RUS_DATA_ETD_MASK) {
            skipBytesUnescaped(buf, readUnsignedByteUnescaped(buf));
        }

        return position;
    }


    private Object decodeSMSForward(ByteBuf buf) {
        return null;
    }


    private Object decodeSerialPassThrough(ByteBuf buf) {
        return null;
    }


    private void printPacketType(short packetType) {
        LOGGER.info("Packet type: " + (packetType == G1RUS_TYPE_HEARTBEAT ? "HEARTBEAT" :
                    "[" + ((packetType & G1RUS_TYPE_IMEI_MASK) == G1RUS_TYPE_IMEI_LONG ? "IMEI_LONG" : "IMEI_SHORT") + "]" +
                    "[" + ((packetType & G1RUS_TYPE_EVENT_MASK) == G1RUS_TYPE_NON_EVENT ? "NON-EVENT" : "EVENT") + "]" +
                    "[" + ((packetType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_REGULAR ? "REGULAR" : (packetType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_SMS_FORWARD ? "SMS FORWARD" : (packetType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_SERIAL_PASS_THROUGH ? "PASS THROUGH" : (packetType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_MIXED ? "MIXED PACKED" : "RESERVED/INVALID") + "]"));
    }


    @Override
    protected Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        if (buf.readUnsignedByte() != G1RUS_HEAD_TAIL) {
            return null;
        }

        LOGGER.info("Protocol version: " + readUnsignedByteUnescaped(buf));

        short packetType = readUnsignedByteUnescaped(buf);
        printPacketType(packetType);

        byte[] imei = new byte[8];
        readBytesUnescaped(buf, imei, 0, 7);
        long imei_long = Longs.fromByteArray(imei);
        LOGGER.info("IMEI: " + imei_long);

        List<Position> positions = null;

        if (packetType == G1RUS_TYPE_HEARTBEAT) {
            return null;
        } else if ((packetType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_REGULAR) {
            positions = new LinkedList<>();
            Position position = decodeRegular(channel, remoteAddress, buf, imei_long, packetType);
            if (position != null) {
                positions.add(position);
            }
        } else if ((packetType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_SMS_FORWARD) {
            return decodeSMSForward(buf);
        } else if ((packetType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_SERIAL_PASS_THROUGH) {
            return decodeSerialPassThrough(buf);
        } else if ((packetType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_MIXED) {
            positions = new LinkedList<>();

            while (buf.readableBytes() > 5) {
                short subPacketLength = readUnsignedShortUnescaped(buf);
                short subPacketType = readUnsignedByteUnescaped(buf);
                printPacketType(subPacketType);

                if ((subPacketType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_REGULAR) {
                    Position position = decodeRegular(channel, remoteAddress, buf, imei_long, subPacketType);
                    if (position != null) {
                        positions.add(position);
                    }
                } else {
                    skipBytesUnescaped(buf, subPacketLength - 1);
                }
                /* else if ((subPacketType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_SMS_FORWARD) {
                    skipBytesUnescaped(buf, subPacketLength - 1);
                    *//*decodeSMSForward(buf);*//*
                } else if ((subPacketType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_SERIAL_PASS_THROUGH) {
                    skipBytesUnescaped(buf, subPacketLength - 1);
                    *//*decodeSerialPassThrough(buf);*//*
                }*/
            }
        } else {
            LOGGER.error("Unknown packet type!");
        }

        skipBytesUnescaped(buf, 2); /* CRC */ /* TODO: actually check it */
        short tail = buf.readUnsignedByte();
        if (tail == G1RUS_HEAD_TAIL) {
            LOGGER.info("Tail: OK");
        } else {
            LOGGER.error("Tail: FAIL!");
        }

        return positions;
    }
}
