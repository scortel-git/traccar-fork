/*
 * Copyright 2023 Anton Tananaev (anton@traccar.org)
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

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.Channel;
import jakarta.inject.Inject;
import org.traccar.BaseProtocolDecoder;
import org.traccar.Protocol;
import org.traccar.helper.BitUtil;
import org.traccar.helper.DateBuilder;
import org.traccar.helper.UnitsConverter;
import org.traccar.model.CellTower;
import org.traccar.model.Network;
import org.traccar.model.Position;
import org.traccar.model.PriorNotification;
import org.traccar.session.DeviceSession;
import org.traccar.storage.Storage;
import org.traccar.storage.StorageException;
import org.traccar.storage.query.Columns;
import org.traccar.storage.query.Request;

import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class ElbBaseProtocolDecoder extends BaseProtocolDecoder {

    public ElbBaseProtocolDecoder(Protocol protocol) {
        super(protocol);
    }

    @Inject
    protected Storage storage;
    public static final byte MSG_START = 0x2;
    public static final byte MSG_END = 0x3;
    public static final byte MSG_SOFTWARE_VERSION = 0x21;
    public static final byte MSG_SHELL_VERSION = 0x22;
    public static final byte MSG_CREW_INFO = 0x23;
    public static final byte MSG_VESSEL_INFO = 0x24;
    public static final byte MSG_FULL_FISHING_TRIP_REPORT = 0x25;
    public static final byte MSG_CURRENT_FISHING_DAY_INFORMATION = 0x26;
    public static final byte MSG_CONFIGURATION = 0x2E;
    public static final byte MSG_START_FISHING_TRIP = 0x30;
    public static final byte MSG_END_FISHING_TRIP = 0x31;
    public static final byte MSG_PORT_LEAVE = 0x32;
    public static final byte MSG_PORT_ARRIVAL = 0x33;
    public static final byte MSG_START_FISHING_OPERATION = 0x34;
    public static final byte MSG_END_FISHING_OPERATION = 0x35;
    public static final byte MSG_24_DAY_REPORT = 0x36;
    public static final byte MSG_FO_DATA = 0x37;
    public static final byte MSG_LANDING_DECLARATION = 0x40;
    public static final byte MSG_INSPECTION_DATA = 0x41;
    public static final byte MSG_LOST_GEAR = 0x42;
    public static final byte MSG_GET_METEO_DATA = 0x50;
    public static final byte MSG_UPDATE_CERTIFICATES = 0x52;
    public static final byte MSG_GET_DEVICE_UNIVERSAL_TIME = 0x60;
    public static final byte MSG_DEVICE_UNIVERSAL_TIME = 0x61;
    public static final byte MSG_UNKNOWN_FRAME_TYPE = 0x71;
    public static final byte MSG_INVALID_PARAMETER = 0x72;
    public static final byte MSG_IDP_POLL_CMD = 0x73;
    public static final byte MSG_IGNORE_CRC_SUM = 0x76;
    public static final byte MSG_ERROR_MODULE_ID = 0x7F;
    public static final byte MSG_IN_GET_IN_FULL_DATA = (byte) 0x80;
    public static final byte MSG_IN_FULL_ASPS_DATA = (byte) 0x81;
    public static final byte MSG_IN_GET_INASPS_DATA = (byte) 0x82;
    public static final byte MSG_IN_ASPS_DATA = (byte) 0x83;
    public static final byte MSG_IN_GET_INSSCB_DATA = (byte) 0x84;
    public static final byte MSG_IN_SSCB_DATA = (byte) 0x85;
    public static final byte MSG_IN_GET_INOECB_DATA = (byte) 0x86;
    public static final byte MSG_IN_OECB_DATA = (byte) 0x87;


    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    private long deviceId = 0;
    protected void saveObject(Object object, Storage storage) throws StorageException {

        if (object instanceof PriorNotification) {
            PriorNotification priorNotification = (PriorNotification) object;
            priorNotification.setId(storage.addObject(priorNotification, new Request(new Columns.Exclude("id"))));
        }

    }

    private String decodeAlarm(int value) {
        switch (value) {
            case 4:
                return Position.ALARM_LOW_BATTERY;
            case 6:
                return Position.ALARM_POWER_RESTORED;
            case 10:
                return Position.ALARM_SOS;
            case 13:
                return Position.ALARM_BRAKING;
            case 14:
                return Position.ALARM_ACCELERATION;
            case 17:
                return Position.ALARM_OVERSPEED;
            case 23:
                return Position.ALARM_ACCIDENT;
            default:
                return null;
        }
    }

    protected void decodeInternal(Object msg, Position position, Storage database) {
        ByteBuf buf = (ByteBuf) msg;

        buf.readByte(); // STX
        buf.readByte(); // Sequence
        byte mask = buf.readByte();

        switch (mask) {
            case MSG_FO_DATA:
                break;
            case MSG_24_DAY_REPORT:
                break;
            case MSG_CURRENT_FISHING_DAY_INFORMATION:
                break;
            case MSG_CREW_INFO:
                break;
            case MSG_DEVICE_UNIVERSAL_TIME:
                break;
            case MSG_END_FISHING_OPERATION:
                break;
            case MSG_END_FISHING_TRIP:


                break;
            case MSG_ERROR_MODULE_ID:
                break;
            case MSG_FULL_FISHING_TRIP_REPORT:
                break;
            case MSG_GET_DEVICE_UNIVERSAL_TIME:
                break;
            case MSG_GET_METEO_DATA:
                break;
            case MSG_IDP_POLL_CMD:
                break;
            case MSG_IGNORE_CRC_SUM:
                break;
            case MSG_INSPECTION_DATA:
                break;
            case MSG_INVALID_PARAMETER:
                break;
            case MSG_LANDING_DECLARATION:

                break;
            case MSG_LOST_GEAR:
                break;
            case MSG_PORT_ARRIVAL:
                break;
            case MSG_PORT_LEAVE:
                break;
            case MSG_SHELL_VERSION:
                break;
            case MSG_SOFTWARE_VERSION:
                break;
            case MSG_START_FISHING_OPERATION:
                break;
            case MSG_START_FISHING_TRIP:
                break;
            case MSG_UNKNOWN_FRAME_TYPE:
                break;
            case MSG_UPDATE_CERTIFICATES:
                break;
            case MSG_VESSEL_INFO:
                break;
            case MSG_IN_FULL_ASPS_DATA:
                break;
            case MSG_IN_ASPS_DATA:
                break;
            case MSG_IN_SSCB_DATA:
                break;
            case MSG_IN_OECB_DATA:
                break;
            default:
                break;
        }
        PriorNotification priorNotification = new PriorNotification();
        priorNotification.setProtocol(getProtocolName());
        priorNotification.setDeviceId(deviceId);
        priorNotification.setAltitude(0.0);
        priorNotification.setCourse(120);
        priorNotification.setTime(new Date());
        priorNotification.setValid(true);
        priorNotification.setOutdated(false);
        position.set(Position.KEY_EVENT, Position.KEY_PRIOR_NOTIFICATION);
        position.setPriorNotification(priorNotification);

    }

    @Override
    protected Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {
        Position position = new Position(getProtocolName());
        if (deviceId == 0) {
            ByteBuf buffer = (ByteBuf) msg;
            ByteBuf buf = buffer.copy();
            buf.readByte(); // STX
            String deviceUniqueId = buf.readCharSequence(15, StandardCharsets.US_ASCII).toString();
            DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, deviceUniqueId);
            if (deviceSession == null) {
                return null;
            }
            setDeviceId(deviceSession.getDeviceId());
        }
        decodeInternal(msg, position, storage);


        return position;
    }

}
