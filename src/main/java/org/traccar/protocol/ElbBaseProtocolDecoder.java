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
import jakarta.xml.bind.DatatypeConverter;
import org.traccar.BaseProtocolDecoder;
import org.traccar.Protocol;
import org.traccar.model.*;
import org.traccar.session.DeviceSession;
import org.traccar.storage.Storage;
import org.traccar.storage.StorageException;
import org.traccar.storage.query.Columns;
import org.traccar.storage.query.Condition;
import org.traccar.storage.query.Request;

import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.*;

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

    protected void decodeInternal(Object msg, Position position, Storage database) {


        ByteBuf buf = (ByteBuf) msg;
        ByteBuf rawData = (ByteBuf) msg;

        buf.readByte(); // STX
        buf.readByte(); // Sequence
        byte mask = buf.readByte();
        buf.readByte(); // content
        String deviceUniqueId = buf.readCharSequence(15, StandardCharsets.US_ASCII).toString();

        switch (mask) {
            case MSG_FO_DATA:
                position.set("MSG_FO_DATA", ByteBufUtil.hexDump((ByteBuf) msg));
                break;
            case MSG_24_DAY_REPORT:
                position.set("MSG_24_DAY_REPORT", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_CURRENT_FISHING_DAY_INFORMATION:

                position.set("MSG_CURRENT_FISHING_DAY_INFORMATION", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_CREW_INFO:
                position.set("MSG_CREW_INFO", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_DEVICE_UNIVERSAL_TIME:
                position.set("MSG_DEVICE_UNIVERSAL_TIME", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_END_FISHING_OPERATION:
                position.set("MSG_END_FISHING_OPERATION", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_END_FISHING_TRIP:
                position.set("MSG_END_FISHING_TRIP", DatatypeConverter.printHexBinary(rawData.array()));

                Device device = null;
                long driverId = -1;
                try {
                    device = database.getObject(Device.class, new Request(
                            new Columns.All(), new Condition.Equals("uniqueId", deviceUniqueId)));
                } catch (StorageException ignore) {
                }
                try {
                    List<Permission> permissions = database.getPermissions(Device.class, Driver.class);
                    if (!permissions.isEmpty()) {
                        driverId = permissions.get(0).getPropertyId();
                    }

                } catch (StorageException ignored) {

                }

                int counter = 1;
                assert device != null;
                Map<String, Object> attributes = device.getAttributes();
                ElbEndFishingTrip trip = new ElbEndFishingTrip();
                trip.setDriverId(driverId);
                trip.setProtocol("endFishingTrip");
                trip.setEstimatedArriveTime(new Date((trip.ADDITIONAL_SECONDS + buf.readIntLE()) * 1000));
                trip.setLandingPortId(buf.readShortLE());
                trip.setEndFishingTripTime(new Date((trip.ADDITIONAL_SECONDS + buf.readIntLE()) * 1000));
                trip.setTime(new Date((trip.ADDITIONAL_SECONDS + buf.readIntLE()) * 1000));
                trip.setLatitude((buf.readIntLE() & 0xFFFFFFFFL) / 60000.0);
                trip.setLongitude((buf.readIntLE() & 0xFFFFFFFFL) / 60000.0);
                trip.setSpeed((double) (buf.readShortLE() & 0xFFFFL) / 10);
                trip.setCourse((double) (buf.readShortLE() & 0xFFFFL));
                trip.setCreationDate(new Date((trip.ADDITIONAL_SECONDS + buf.readIntLE()) * 1000));
                int tripLength = buf.readByte();
                trip.setTripNumber(
                        buf.readCharSequence(
                                        tripLength,
                                        StandardCharsets.UTF_8)
                                .toString());
                trip.set("analog1", buf.readIntLE());
                trip.set("analog2", buf.readIntLE());
                trip.set("dio1", buf.readByte());
                trip.setDeviceId(position.getDeviceId());
                trip.setPositionId(position.getId());
                trip.setOutdated(false);
                trip.setValid(true);
                trip.setOutdated(false);
                trip.setDeviceId(deviceId);
                if (driverId > 0) {
                    trip.setDriverId(driverId);
                }

                String cfr = attributes.get("cfr").toString();

                List<ElbEndFishingTrip> oldElbEndFishingTrips = null;
                try {
                    oldElbEndFishingTrips = database.getObjects(ElbEndFishingTrip.class,
                            new Request(
                                    new Columns.All(), new Condition.And(
                                    new Condition.Equals("tripNumber", trip.getTripNumber()),
                                    new Condition.Equals("outdated", false)
                            )
                            )
                    );
                } catch (StorageException ignore) {
                }
                assert oldElbEndFishingTrips != null;
                boolean isDublicated = false;

                if (!oldElbEndFishingTrips.isEmpty()) {
                    for (ElbEndFishingTrip previous : oldElbEndFishingTrips) {
                        if (Objects.equals(trip.getDeviceTime().toString(), previous.getDeviceTime().toString())){
                            isDublicated = true;
                            break;
                        }

                        try {
                            trip.setUniqueNumber(previous.getUniqueNumber());
                            previous.setOutdated(true);
                            database.updateObject(previous, new Request(
                                    new Columns.Exclude("id"),
                                    new Condition.Equals("id", previous.getId())));
                        } catch (StorageException ignore) {
                        }
                    }

                }else {
                    String uniqueNumber = !Objects.equals(cfr, null) ? cfr : device.getUniqueId();
                    long elbEndFishingTripCounts = -1;
                    try {
                        var conditions = new LinkedList<Condition>();
                        conditions.add(new Condition.Equals("deviceId", device.getId()));
                        conditions.add(new Condition.Equals("outdated", false));

                        elbEndFishingTripCounts = database.getObjectsCount(ElbEndFishingTrip.class,
                                new Request(
                                        new Columns.All(),
                                        Condition.merge(conditions)
                                )
                        );
                    } catch (StorageException ignore) {

                    }

                    trip.setUniqueNumber(
                            uniqueNumber + "-" + String.format(
                                    "%02d",
                                    elbEndFishingTripCounts > 0 ?
                                            elbEndFishingTripCounts + counter
                                            : counter));
                }

                position.setLatitude(trip.getLatitude());
                position.setLongitude(trip.getLongitude());
                position.setAltitude(0.00);
                position.setTime(trip.getDeviceTime());
                position.setSpeed(trip.getSpeed());
                position.setCourse(trip.getCourse());
                if (!isDublicated) {
                    position.set(Position.KEY_EVENT, Position.KEY_ELB_NOTIFICATION);
                    position.setElbObject(trip);
                    position.setValid(true);
                }
                else {
                    position.setValid(false);
                }
                break;
            case MSG_ERROR_MODULE_ID:
                position.set("MSG_ERROR_MODULE_ID", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_FULL_FISHING_TRIP_REPORT:
                position.set("MSG_FULL_FISHING_TRIP_REPORT", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_GET_DEVICE_UNIVERSAL_TIME:
                position.set("MSG_GET_DEVICE_UNIVERSAL_TIME", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_GET_METEO_DATA:
                position.set("MSG_GET_METEO_DATA", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_IDP_POLL_CMD:
                position.set("MSG_IDP_POLL_CMD", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_IGNORE_CRC_SUM:
                position.set("MSG_IGNORE_CRC_SUM", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_INSPECTION_DATA:
                position.set("MSG_INSPECTION_DATA", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_INVALID_PARAMETER:
                position.set("MSG_INVALID_PARAMETER", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_LANDING_DECLARATION:
                position.set("MSG_LANDING_DECLARATION", DatatypeConverter.printHexBinary(rawData.array()));
                int tripSize = buf.readByte();
                String tripNumber = buf.readCharSequence(
                                tripSize,
                                StandardCharsets.UTF_8)
                        .toString();
                int FORecLength = buf.readByte();

                break;
            case MSG_LOST_GEAR:
                position.set("MSG_LOST_GEAR", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_PORT_ARRIVAL:
                position.set("MSG_PORT_ARRIVAL", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_PORT_LEAVE:
                position.set("MSG_PORT_LEAVE", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_SHELL_VERSION:
                position.set("MSG_SHELL_VERSION", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_SOFTWARE_VERSION:
                position.set("MSG_END_FISHING_TRIP", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_START_FISHING_OPERATION:
                position.set("MSG_START_FISHING_OPERATION", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_START_FISHING_TRIP:
                position.set("MSG_START_FISHING_TRIP", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_UNKNOWN_FRAME_TYPE:
                position.set("MSG_UNKNOWN_FRAME_TYPE", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_UPDATE_CERTIFICATES:
                position.set("MSG_UPDATE_CERTIFICATES", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_VESSEL_INFO:
                position.set("MSG_VESSEL_INFO", DatatypeConverter.printHexBinary(rawData.array()));
                /* TODO */
                break;
            case MSG_IN_FULL_ASPS_DATA:
                position.set("MSG_IN_FULL_ASPS_DATA", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_IN_ASPS_DATA:
                position.set("MSG_IN_ASPS_DATA", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            case MSG_IN_SSCB_DATA:
                position.set("MSG_END_FISHING_TRIP", DatatypeConverter.printString(Arrays.toString(rawData.array())));

                break;
            case MSG_IN_OECB_DATA:
                position.set("MSG_END_FISHING_TRIP", DatatypeConverter.printHexBinary(rawData.array()));

                break;
            default:
                break;
        }
    }

    @Override
    protected Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {
        List<Position> positions = new LinkedList<>();
        Position position = new Position(getProtocolName());

        String deviceUniqueId;

        ByteBuf buffer = (ByteBuf) msg;
        ByteBuf buf = buffer.copy();
        buf.readByte(); // STX
        buf.readByte(); // seq
        buf.readByte(); // mask
        buf.readByte(); // content
        deviceUniqueId = buf.readCharSequence(15, StandardCharsets.US_ASCII).toString();
        DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, deviceUniqueId);
        if (deviceSession == null) {
            return null;
        }
        position.setDeviceId(deviceSession.getDeviceId());


        decodeInternal(msg, position, storage);

        positions.add(position);


        return positions;
    }

}
