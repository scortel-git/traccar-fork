package org.traccar.protocol;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
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


    private void decodeSYSSub(ByteBuf buf) {
        LOGGER.info("<SYS>");

        buf.skipBytes(1); /* Total length */

        /* NOTE: assuming order:
         * Device name -> Firmware version -> Hardware version.
         * TODO: actually check it.
         */

        /* Device name */
        byte devNameLen = (byte) buf.readUnsignedByte();
        byte[] devName = new byte[devNameLen & 0xF];
        buf.readBytes(devName);
        String devNameString = new String(devName);
        LOGGER.info("Device name: " + devNameString);

        /* Firmware version */
        byte firmwareLen = (byte) buf.readUnsignedByte();
        byte[] firmware = new byte[firmwareLen & 0xF];
        buf.readBytes(firmware);
        String firmwareString = new String(firmware);
        LOGGER.info("Firmware version: " + firmwareString);

        /* Hardware version */
        byte hardwareLen = (byte) buf.readUnsignedByte();
        byte[] hardware = new byte[hardwareLen & 0xF];
        buf.readBytes(hardware);
        String hardwareString = new String(hardware);
        LOGGER.info("Hardware version: " + hardwareString);

        LOGGER.info("</SYS>");
    }


    private void decodeGPSSub(ByteBuf buf, Position position) {
        LOGGER.info("<GPS>");

        buf.skipBytes(1); /* Total length */

        short subMask = (short) buf.readUnsignedShort();
        if ((subMask & G1RUS_GPS_SIGN_MASK) == G1RUS_GPS_SIGN_MASK) {
            buf.skipBytes(1);
        }
        if ((subMask & G1RUS_GPS_POS_MASK) == G1RUS_GPS_POS_MASK) {
            byte[] pos_buf = new byte[4];
            buf.readBytes(pos_buf);
            position.setLatitude((float) Ints.fromByteArray(pos_buf) / 1000000);
            LOGGER.info("Latitude: " + position.getLatitude());

            buf.readBytes(pos_buf);
            position.setLongitude((float) Ints.fromByteArray(pos_buf) / 1000000);
            LOGGER.info("Longitude: " + position.getLongitude());
        }
        if ((subMask & G1RUS_GPS_SPD_MASK) == G1RUS_GPS_SPD_MASK) {
            position.setSpeed(buf.readUnsignedShort());
            LOGGER.info("Speed: " + position.getSpeed());
        }
        if ((subMask & G1RUS_GPS_AZTH_MASK) == G1RUS_GPS_AZTH_MASK) {
            position.setCourse(buf.readUnsignedShort());
            LOGGER.info("Course: " + position.getCourse());
        }
        if ((subMask & G1RUS_GPS_ALT_MASK) == G1RUS_GPS_ALT_MASK) {
            position.setAltitude(buf.readUnsignedShort());
            LOGGER.info("Altitude: " + position.getAltitude());
        }
        if ((subMask & G1RUS_GPS_HDOP_MASK) == G1RUS_GPS_HDOP_MASK) {
            buf.skipBytes(2);
        }
        if ((subMask & G1RUS_GPS_VDOP_MASK) == G1RUS_GPS_VDOP_MASK) {
            buf.skipBytes(2);
        }

        LOGGER.info("</GPS>");
    }


    private void decodeADCSub(ByteBuf buf, Position position) {

    }


    private Position decodeRegular(Channel channel, SocketAddress remoteAddress, ByteBuf buf, long imei, byte packetType) {
        int timestamp_ = buf.readInt();
        long timestamp = (946684800 + timestamp_) * 1000L; /* Convert received time to proper UNIX timestamp */
        LOGGER.info("Date and time: " + new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date(timestamp)));

        if ((packetType & G1RUS_TYPE_EVENT_MASK) != G1RUS_TYPE_NON_EVENT) {
            buf.skipBytes(1); /* Event ID */
        }

        DeviceSession deviceSession = null;
        Position position = null;

        short dataUploadingMask = (short) buf.readUnsignedShort();
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
            buf.skipBytes(buf.readUnsignedByte());
        }
        if ((dataUploadingMask & G1RUS_DATA_COT_MASK) == G1RUS_DATA_COT_MASK) {
            buf.skipBytes(buf.readUnsignedByte());
        }
        if ((dataUploadingMask & G1RUS_DATA_ADC_MASK) == G1RUS_DATA_ADC_MASK) {
            /*if (deviceSession == null) {
                return null;
            }*/

            buf.skipBytes(buf.readUnsignedByte());
            // decodeADCSub(buf, position);
        }
        if ((dataUploadingMask & G1RUS_DATA_DTT_MASK) == G1RUS_DATA_DTT_MASK) {
            buf.skipBytes(buf.readUnsignedByte());
        }
        if ((dataUploadingMask & G1RUS_DATA_ETD_MASK) == G1RUS_DATA_ETD_MASK) {
            buf.skipBytes(buf.readUnsignedByte());
        }

        return position;
    }


    private Object decodeSMSForward(ByteBuf buf) {
        return null;
    }


    private Object decodeSerialPassThrough(ByteBuf buf) {
        return null;
    }


    private void printPacketType(byte packetType) {
        LOGGER.info("Packet type: " + (packetType == G1RUS_TYPE_HEARTBEAT ? "HEARTBEAT" :
                    "[" + ((packetType & G1RUS_TYPE_IMEI_MASK) == G1RUS_TYPE_IMEI_LONG ? "IMEI_LONG" : "IMEI_SHORT") + "]" +
                    "[" + ((packetType & G1RUS_TYPE_EVENT_MASK) == G1RUS_TYPE_NON_EVENT ? "NON-EVENT" : "EVENT") + "]" +
                    "[" + ((packetType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_REGULAR ? "REGULAR" : (packetType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_SMS_FORWARD ? "SMS FORWARD" : (packetType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_SERIAL_PASS_THROUGH ? "PASS THROUGH" : (packetType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_MIXED ? "MIXED PACKED" : "RESERVED/INVALID") + "]"));
    }


    @Override
    protected Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        buf.skipBytes(1); /* Head */

        LOGGER.info("Protocol version: " + buf.readUnsignedByte());

        byte packetType = (byte) buf.readUnsignedByte();
        printPacketType(packetType);

        byte[] imei = new byte[8];
        buf.readBytes(imei, 0, 7);
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

            while (buf.readableBytes() > 3) {
                short subPacketLength = (short) buf.readUnsignedShort();
                byte subPacketType = (byte) buf.readUnsignedByte();
                printPacketType(subPacketType);

                if ((subPacketType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_REGULAR) {
                    Position position = decodeRegular(channel, remoteAddress, buf, imei_long, packetType);
                    if (position != null) {
                        positions.add(position);
                    }
                } else {
                    try {
                        buf.skipBytes(subPacketLength - 1);
                    } catch (Exception e) {
                        return positions;
                    }
                }
                /* else if ((subPacketType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_SMS_FORWARD) {
                    buf.skipBytes(subPacketLength - 1);
                    *//*decodeSMSForward(buf);*//*
                } else if ((subPacketType & G1RUS_TYPE_BCD_MASK) == G1RUS_TYPE_SERIAL_PASS_THROUGH) {
                    buf.skipBytes(subPacketLength - 1);
                    *//*decodeSerialPassThrough(buf);*//*
                }*/
            }
        } else {
            LOGGER.error("Unknown packet type!");
        }

        buf.skipBytes(2); /* CRC */ /* TODO: actually check it */
        // buf.skipBytes(1); /* Tail */
        byte tail = (byte) buf.readUnsignedByte();

        return positions;
    }
}
