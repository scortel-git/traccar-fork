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

import com.fasterxml.jackson.core.JsonProcessingException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.Channel;
import jakarta.inject.Inject;
import jakarta.xml.bind.DatatypeConverter;
import org.traccar.BaseProtocolDecoder;
import org.traccar.Protocol;
import org.traccar.helper.BitUtil;
import org.traccar.model.*;
import org.traccar.session.DeviceSession;
import org.traccar.storage.Storage;

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
        int error = 0;
        long i = position.getLong("messageId");
        if (i == 15301654256L) {
            error = 9;
        }
        ByteBuf buf = (ByteBuf) msg;
        ByteBuf rawData = (ByteBuf) msg;

        byte stx = buf.readByte(); // STX
        byte seq = buf.readByte(); // Sequence
        byte mask = buf.readByte();
        byte content = buf.readByte(); // content
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

                try {
                    position.set("MSG_END_FISHING_TRIP", DatatypeConverter.printHexBinary(rawData.array()));

                    ElbEndFishingTrip trip = new ElbEndFishingTrip();
                    trip.setProtocol("endFishingTrip");
                    trip.setEstimatedArriveTime(new Date((trip.ADDITIONAL_SECONDS + buf.readIntLE()) * 1000));
                    short portId = buf.readShortLE();
                    ElbPorts elbPort = ElbPorts.elbPortsHashMap.getOrDefault(portId, new ElbPorts());
                    elbPort.setPortId(portId);
                    trip.setLandingPortId(elbPort.getPortId());
                    trip.setLandingPortCode(elbPort.getCode());

                    trip.setEndFishingTripTime(new Date( (trip.ADDITIONAL_SECONDS + buf.readIntLE() ) * 1000));
                    trip.setTime(
                            new Date((trip.ADDITIONAL_SECONDS + buf.readIntLE()) * 1000)
                    );

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

                    position.setLatitude(trip.getLatitude());
                    position.setLongitude(trip.getLongitude());
                    position.setAltitude(239);
                    position.setTime(trip.getDeviceTime());
                    position.setSpeed(trip.getSpeed());
                    position.setCourse(trip.getCourse());

                    position.set(Position.KEY_EVENT, Position.KEY_END_FISHING_TRIP);
                    position.setElbObject(trip);
                    position.setValid(true);
                }catch (Exception e) {
                    error = 4;
                }




//                if (isDublicated) {
//                    position.set("duplicated", "true");
//                    position.setProtocol("duplicated");
//
//                    position.setValid(false);
//                }
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
                try {
                    position.set("MSG_LANDING_DECLARATION", DatatypeConverter.printHexBinary(rawData.array()));

                    ElbLandingDeclaration elbLandingDeclaration = new ElbLandingDeclaration();
                    elbLandingDeclaration.setProtocol("landingDeclaration");
                    elbLandingDeclaration.setTripNumber(buf.readCharSequence(
                                    content,
                                    StandardCharsets.UTF_8)
                            .toString());

                    int fcRecLength = buf.readByte();
                    var elbCatches = new LinkedList<Object>();
                    ArrayList<Short> fishesIds = new ArrayList<Short>();
                    try {
                        while (fcRecLength > 0) {
                            byte fcContent = buf.readByte();

                            boolean isAdditional = BitUtil.check(fcContent, 6);
                            boolean isBms = BitUtil.check(fcContent, 5);
                            boolean isRecDeleted = BitUtil.check(fcContent, 4);
                            boolean isDiscardedData = BitUtil.check(fcContent, 3);
                            boolean isDataCorrection = BitUtil.check(fcContent, 2);
                            boolean isDiscardedRecCrDisDiscardedRecCrDtt = BitUtil.check(fcContent, 1);

                            byte speciesContent = buf.readByte();
                            boolean isSpeciesFullInformation = BitUtil.check(speciesContent, 7);
                            boolean isSpeciesDataCorrection = BitUtil.check(speciesContent, 2);
                            boolean isSpeciesDiscardedRecCrDt = BitUtil.check(speciesContent, 1);
                            ElbSpeciesExtended elbSpecies = new ElbSpeciesExtended();
                            short sequence = buf.readShortLE();
                            fishesIds.add(sequence);
                            elbSpecies.setElbSpeciesExtended(sequence); // speciesSequenceNumber // code

                            elbSpecies.set("price", isSpeciesFullInformation ? buf.readFloatLE() : 0);
                            elbSpecies.set("currency", isSpeciesFullInformation ? buf.readByte() : 0);

                            new Date((1514764800L + buf.readIntLE()) * 1000); // crDate1
                            Date crDate2 = isSpeciesDiscardedRecCrDt ? new Date((1514764800L + buf.readIntLE()) * 1000) : null;

                            elbSpecies.setPresentation(ElbSpeciesPresentation.getSpeciesPresentation(buf.readByte()).getCode().toUpperCase());
                            elbSpecies.set("conditionSequenceNumber", buf.readByte());
                            elbSpecies.set("quantityType", buf.readByte());

                            elbSpecies.set("discardedWeight", isDiscardedData ? buf.readIntLE() : 0);
                            elbSpecies.set("discardedCount", isDiscardedData ? buf.readShortLE() : 0);
                            elbSpecies.set("discardedType", isDiscardedData ? buf.readByte() : 0);

                            elbSpecies.set("speciesWeight", buf.readIntLE());
                            elbSpecies.set("speciesWeightCount", buf.readShortLE());

                            Date speciesCrDate = new Date((1514764800L + buf.readIntLE()) * 1000);
                            Date DiscardedSpeciesCrDate = isDiscardedRecCrDisDiscardedRecCrDtt ? new Date((1514764800L + buf.readIntLE()) * 1000) : null;

                            elbSpecies.set("speciesSizeGroup", buf.readByte());
                            elbSpecies.set("qtCount", buf.readShortLE());
                            elbSpecies.setId(fcRecLength);

                            elbCatches.add(elbSpecies);
                            fcRecLength--;


                        }

                    }catch (Exception e) {
                        error =2;
                    }

                    try {
                        elbLandingDeclaration.setCatches(elbCatches);
                    } catch (JsonProcessingException e) {
                        error = 3;
                    }
                    elbLandingDeclaration.setFishIds(fishesIds);
                    elbLandingDeclaration.setTime(new Date((1514764800L + buf.readIntLE()) * 1000));
                    elbLandingDeclaration.setLatitude((buf.readIntLE() & 0xFFFFFFFFL) / 60000.0);
                    elbLandingDeclaration.setLongitude((buf.readIntLE() & 0xFFFFFFFFL) / 60000.0);
                    elbLandingDeclaration.setSpeed((double) (buf.readShortLE() & 0xFFFFL) / 10);
                    elbLandingDeclaration.setCourse((double) (buf.readShortLE() & 0xFFFFL));
                    elbLandingDeclaration.set(Position.PREFIX_ADC+1, buf.readIntLE());
                    elbLandingDeclaration.set(Position.PREFIX_ADC+2, buf.readIntLE());
                    elbLandingDeclaration.set(Position.PREFIX_IO+1, buf.readByte());
                    elbLandingDeclaration.setDeviceId(position.getDeviceId());

                    int fgListLength = buf.readByte();

                    int fgListSize = fgListLength + buf.readerIndex() > buf.writerIndex() ? buf.writerIndex() - buf.readerIndex() : fgListLength;
                    try {

                        elbLandingDeclaration.set("gears", buf.readCharSequence(
                                        fgListSize,
                                        StandardCharsets.US_ASCII)
                                .toString());
                    }catch (Exception e) {
                        int y = 0;
                    }
                    position.setTime(elbLandingDeclaration.getDeviceTime());
                    position.setLatitude(elbLandingDeclaration.getLatitude());
                    position.setLongitude(elbLandingDeclaration.getLongitude());
                    position.setSpeed(elbLandingDeclaration.getSpeed());
                    position.setCourse(elbLandingDeclaration.getCourse());
                    position.set(Position.PREFIX_ADC+1,
                            elbLandingDeclaration.getInteger(Position.PREFIX_ADC+1));
                    position.set(Position.PREFIX_ADC+2,
                            elbLandingDeclaration.getInteger(Position.PREFIX_ADC+2));
                    position.set(Position.PREFIX_IO+1,
                            elbLandingDeclaration.getInteger(Position.PREFIX_IO+1));
                    position.set(Position.KEY_EVENT, Position.KEY_LANDING_DECLARATION);
                    position.setElbObject(elbLandingDeclaration);
                    position.setValid(true);
                }catch (Exception e) {
                    error = 2;
                }

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
                try {
                    ElbStartFishingTrip elbStartFishingTrip = new ElbStartFishingTrip();
                    elbStartFishingTrip.setProtocol("elbStartFishingTrip");
                    final byte EcoZone_bit_position = 7;
                    final byte StatZone_bit_position = 6;
                    final byte StGen_bit_position = 5;
                    final byte Pos_bit_position = 4;
                    final byte TimeSt_bit_position = 3;
                    final byte PortId_bit_position = 3;
                    final byte bit_pos_datacorrection = 2;
                    final byte bit_pos_include_disreccrdt = 1;

                    position.set("MSG_START_FISHING_TRIP", DatatypeConverter.printHexBinary(rawData.array()));


                    byte sftContent = buf.readByte();
                    int timestamp = buf.readIntLE();
                    Date sftDate = new Date((1514764800L + timestamp) * 1000);
                    elbStartFishingTrip.setTime(sftDate);

                    Date disRecCrDTL = BitUtil.check(sftContent, bit_pos_datacorrection) ?
                            new Date((1514764800L + buf.readIntLE()) * 1000) : null;

                    elbStartFishingTrip.set("disRecCrDTL", disRecCrDTL != null ? disRecCrDTL.toString() : null);
                    short eftDeparturePortPortId = BitUtil.check(sftContent, PortId_bit_position) ? buf.readShortLE() : -1;
                    elbStartFishingTrip.setDeparturePortId(eftDeparturePortPortId);

                    ElbPorts sftElbPort = eftDeparturePortPortId != -1 ?
                            ElbPorts.elbPortsHashMap.getOrDefault(eftDeparturePortPortId, new ElbPorts())
                            : null;
                    String eftDeparturePort = sftElbPort != null ? sftElbPort.getCode() : "";
                    elbStartFishingTrip.setDeparturePortCode(eftDeparturePort);
                    int fgCount = buf.readByte();
                    var fgRecs = new LinkedList<Object>();

                    while (fgCount > 0) {
                        byte fgContent = buf.readByte();
                        int fishingGearId = buf.readByte();
                        int gearEye = BitUtil.check(fgContent, 7) ? buf.readShortLE() : null;

                        int gearLength = buf.readShortLE();
                        int gearHeight = buf.readShortLE();
                        int gearTradeMark = BitUtil.check(fgContent, 6) ? buf.readShortLE() : -1;
                        gearTradeMark = gearTradeMark == 0 ? 1  : gearTradeMark;

                        int fgModel = BitUtil.check(fgContent, 5) ? buf.readShortLE() : -1;
                        fgModel = fgModel == 0 ? 1 : fgModel;

                        int fgCnt = BitUtil.check(fgContent, 4) ? buf.readShortLE() : null;

                        Date creationDTL = new Date((1514764800L + buf.readIntLE()) * 1000);
                        Date disRecCreationDTL = BitUtil.check(fgContent, 1) ? new Date((1514764800L + buf.readIntLE()) * 1000) : null;


                        fgCount--;
                    }
                    byte activity = buf.readByte();
                    elbStartFishingTrip.set("activity", activity);
                    int ecoZone = BitUtil.check(sftContent, EcoZone_bit_position) ? buf.readShortLE() : null;
                    elbStartFishingTrip.set("ecoZone", ecoZone);
                    int statZone = BitUtil.check(sftContent, StatZone_bit_position) ? buf.readShortLE() : -1;
                    elbStartFishingTrip.set("statZone", statZone);
                    if (BitUtil.check(sftContent, Pos_bit_position)) {
                        elbStartFishingTrip.setTime(new Date((1514764800L + buf.readIntLE()) * 1000));
                        elbStartFishingTrip.setLatitude((buf.readIntLE() & 0xFFFFFFFFL) / 60000.0);
                        elbStartFishingTrip.setLongitude((buf.readIntLE() & 0xFFFFFFFFL) / 60000.0);
                        elbStartFishingTrip.setSpeed((double) (buf.readShortLE() & 0xFFFFL) / 10);
                        elbStartFishingTrip.setCourse((double) (buf.readShortLE() & 0xFFFFL));
                    }
                    int tripNumLength = buf.readByte();
                    if (tripNumLength > buf.readableBytes()) {
                        break;
                    }
                    String tripNum =
                            buf.readCharSequence(
                                            tripNumLength,
                                            StandardCharsets.UTF_8)
                                    .toString();
                    elbStartFishingTrip.setTripNumber(tripNum);
                    position.set(Position.PREFIX_ADC+1, buf.readIntLE());
                    position.set(Position.PREFIX_ADC+2, buf.readIntLE());
                    position.set(Position.PREFIX_IO+1, buf.readByte());
                    int sftFcRecLength = buf.readByte();
                    var sftElbCatches = new LinkedList<Object>();
                    ArrayList<Short> sftFishesIds = new ArrayList<Short>();
                    try {
                        while (sftFcRecLength > 0) {
                            byte fcContent = buf.readByte();

                            boolean isAdditional = BitUtil.check(fcContent, 6);
                            boolean isBms = BitUtil.check(fcContent, 5);
                            boolean isRecDeleted = BitUtil.check(fcContent, 4);
                            boolean isDiscardedData = BitUtil.check(fcContent, 3);
                            boolean isDataCorrection = BitUtil.check(fcContent, 2);
                            boolean isDiscardedRecCrDisDiscardedRecCrDtt = BitUtil.check(fcContent, 1);

                            byte speciesContent = buf.readByte();
                            boolean isSpeciesFullInformation = BitUtil.check(speciesContent, 7);
                            boolean isSpeciesDataCorrection = BitUtil.check(speciesContent, 2);
                            boolean isSpeciesDiscardedRecCrDt = BitUtil.check(speciesContent, 1);
                            ElbSpeciesExtended elbSpecies = new ElbSpeciesExtended();
                            short sequence = buf.readShortLE();
                            sftFishesIds.add(sequence);
                            elbSpecies.setElbSpeciesExtended(sequence); // speciesSequenceNumber // code

                            elbSpecies.set("price", isSpeciesFullInformation ? buf.readFloatLE() : 0);
                            elbSpecies.set("currency", isSpeciesFullInformation ? buf.readByte() : 0);

                            new Date((1514764800L + buf.readIntLE()) * 1000); // crDate1
                            Date crDate2 = isSpeciesDiscardedRecCrDt ? new Date((1514764800L + buf.readIntLE()) * 1000) : null;

                            elbSpecies.setPresentation(ElbSpeciesPresentation.getSpeciesPresentation(buf.readByte()).getCode().toUpperCase());
                            elbSpecies.set("conditionSequenceNumber", buf.readByte());
                            elbSpecies.set("quantityType", buf.readByte());

                            elbSpecies.set("discardedWeight", isDiscardedData ? buf.readIntLE() : 0);
                            elbSpecies.set("discardedCount", isDiscardedData ? buf.readShortLE() : 0);
                            elbSpecies.set("discardedType", isDiscardedData ? buf.readByte() : 0);

                            elbSpecies.set("speciesWeight", buf.readIntLE());
                            elbSpecies.set("speciesWeightCount", buf.readShortLE());

                            Date speciesCrDate = new Date((1514764800L + buf.readIntLE()) * 1000);
                            Date DiscardedSpeciesCrDate = isDiscardedRecCrDisDiscardedRecCrDtt ? new Date((1514764800L + buf.readIntLE()) * 1000) : null;

                            elbSpecies.set("speciesSizeGroup", buf.readByte());
                            elbSpecies.set("qtCount", buf.readShortLE());
                            elbSpecies.setId(sftFcRecLength);

                            sftElbCatches.add(elbSpecies);
                            sftFcRecLength--;

                        }
                        elbStartFishingTrip.setCatches(sftElbCatches);
                        elbStartFishingTrip.setFishIds(sftFishesIds);
                        position.setTime(elbStartFishingTrip.getDeviceTime());
                        position.setLatitude(elbStartFishingTrip.getLatitude());
                        position.setLongitude(elbStartFishingTrip.getLongitude());
                        position.setSpeed(elbStartFishingTrip.getSpeed());
                        position.setCourse(elbStartFishingTrip.getCourse());
                        position.set(Position.PREFIX_ADC+1,
                                elbStartFishingTrip.getInteger(Position.PREFIX_ADC+1));
                        position.set(Position.PREFIX_ADC+2,
                                elbStartFishingTrip.getInteger(Position.PREFIX_ADC+2));
                        position.set(Position.PREFIX_IO+1,
                                elbStartFishingTrip.getInteger(Position.PREFIX_IO+1));
                        position.set(Position.KEY_EVENT, Position.KEY_START_FISHING_TRIP);
                        position.setElbObject(elbStartFishingTrip);
                        position.setValid(true);

                    }catch (Exception e) {
                        error =2;
                    }

                }catch (Exception e) {
                    error = 7;
                }

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
    private String format;


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
