/*
 * Copyright 2022 Anton Tananaev (anton@traccar.org)
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
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpResponse;
import jakarta.inject.Inject;
import org.traccar.BasePipelineFactory;
import org.traccar.BaseProtocolDecoder;
import org.traccar.config.Config;
import org.traccar.session.DeviceSession;
import org.traccar.Protocol;
import org.traccar.helper.UnitsConverter;
import org.traccar.model.Position;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import org.traccar.storage.Storage;
import org.traccar.storage.StorageException;
import org.traccar.storage.query.Columns;
import org.traccar.storage.query.Condition;
import org.traccar.storage.query.Order;
import org.traccar.storage.query.Request;

import java.io.StringReader;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrbcommProtocolDecoder extends BaseProtocolDecoder {

    private OrbcommProtocolPoller poller;

    @Inject
    protected Storage storage;
    public OrbcommProtocolDecoder(Protocol protocol) {
        super(protocol);
    }
    protected Date setInitialTime() {
        Date initialStartTime;
        try {
            Optional<Date> lastServerTimeProtocolObject = storage.getObjects(Position.class,
                            new Request(new Columns.All(),
                                    new Condition.Equals("protocol", getProtocolName()),
                                    new Order("servertime", true, 1)
                            )
                    ).stream()
                    .findFirst()
                    .map(Position::getServerTime);

            initialStartTime = lastServerTimeProtocolObject
                    .map(date1 -> Date.from(date1.toInstant()))
                    .orElseGet(Date::new);
        } catch (StorageException e) {
            initialStartTime = new Date();
        }

        return initialStartTime;
    }

    private boolean isFirstRun = true;

    protected void setIsFirstRun(boolean value) {
        this.isFirstRun = value;
    }
    @Override
    protected Object decode(
            Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {

        if (channel != null && isFirstRun) {
            poller = BasePipelineFactory.getHandler(channel.pipeline(), OrbcommProtocolPoller.class);
            if (poller != null) {
                poller.setStartTime(setInitialTime());
                setIsFirstRun(false);
                return null;
            }
        }

        FullHttpResponse response = (FullHttpResponse) msg;
        String content = response.content().toString(StandardCharsets.UTF_8);
        JsonObject json = Json.createReader(new StringReader(content)).readObject();

        try {
            if (channel != null && !json.getString("NextStartUTC").isEmpty() && !isFirstRun) {
                poller = BasePipelineFactory.getHandler(channel.pipeline(), OrbcommProtocolPoller.class);
                if (poller != null) {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    poller.setStartTime(dateFormat.parse(json.getString("NextStartUTC")));

                }
            }

        } catch (Exception e) {
            if (poller == null) {
                poller = BasePipelineFactory.getHandler(channel.pipeline(), OrbcommProtocolPoller.class);
            }
            assert poller != null;
            poller.setStartTime(setInitialTime());

        }

        try {
            if (json.get("Messages").getValueType() == JsonValue.ValueType.NULL) {
                return null;
            }
            LinkedList<Position> positions = new LinkedList<>();

            JsonArray messages = json.getJsonArray("Messages");
            for (int i = 0; i < messages.size(); i++) {
                try {
                    JsonObject message = messages.getJsonObject(i);
                    DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, message.getString("MobileID"));
                    if (deviceSession != null) {

                        Position position = new Position(getProtocolName());
                        position.setDeviceId(deviceSession.getDeviceId());

                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                        position.setDeviceTime(dateFormat.parse(message.getString("ReceiveUTC")));
                        int sinNummber = message.getInt("SIN");
                        position.set("sin", sinNummber);
                        if (sinNummber == 239) {
                            position.set("fa", true);
                            JsonArray data = message.getJsonArray("RawPayload");

                            ByteBuf buf = Unpooled.buffer();

                            for (int b = 0; b < data.size(); b++) {
                                int intValue = data.getInt(b);
                                byte byteValue = (byte) intValue;
                                buf.writeByte(byteValue);
                            }


                            String hexMask = Integer.toHexString(data.getInt(4));
                            position.set("mask", hexMask);
                            position.set("raw", Arrays.toString(data.toArray()));
                            byte[] uniqueIdBytes = deviceSession.getUniqueId().getBytes(StandardCharsets.US_ASCII);

                            assert channel != null;
                            ByteBuf existingData = (ByteBuf) new ElbFrameDecoder().decode(channel.pipeline().lastContext(), channel, buf);
                            existingData.skipBytes(1);

                            ByteBuf frame = Unpooled.buffer(existingData.readableBytes() + uniqueIdBytes.length + 1);

                            frame.writeByte(0x2);
                            frame.writeBytes(uniqueIdBytes);

                            frame.writeBytes(existingData);
                            ElbBaseProtocol elbBaseProtocol = new ElbBaseProtocol(getConfig());

                            ElbBaseProtocolDecoder elbBaseProtocolDecoder = new ElbBaseProtocolDecoder(elbBaseProtocol);
                            elbBaseProtocolDecoder.setDeviceId(deviceSession.getDeviceId());
                            elbBaseProtocolDecoder.decodeInternal(frame, position, storage);


                        } else if (sinNummber == 237) {


                            JsonObject payload = message.getJsonObject("Payload");
                            JsonArray fields = null;
                            try {
                                fields = payload.getJsonArray("Fields");
                            } catch (Exception ignored) {

                            }
                            if (fields != null) {
                                for (int j = 0; j < fields.size(); j++) {
                                    JsonObject field = fields.getJsonObject(j);
                                    String value = field.getString("Value");
                                    switch (field.getString("Name").toLowerCase()) {
                                        case "eventtime":
                                            position.setDeviceTime(new Date(Long.parseLong(value) * 1000));
                                            break;
                                        case "latitude":
                                            position.setLatitude(Integer.parseInt(value) / 60000.0);
                                            break;
                                        case "longitude":
                                            position.setLongitude(Integer.parseInt(value) / 60000.0);
                                            break;
                                        case "speed":
                                            position.setSpeed(UnitsConverter.knotsFromKph(Integer.parseInt(value)));
                                            break;
                                        case "heading":
                                            int heading = Integer.parseInt(value);
                                            position.setCourse(heading <= 360 ? heading : 0);
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }
                        }
                        if (position.getLatitude() != 0 && position.getLongitude() != 0) {
                            position.setValid(true);
                            position.setFixTime(position.getDeviceTime());
                        } else {
                            getLastLocation(position, position.getDeviceTime());
                        }

                        positions.add(position);

                    }
                    } catch (Exception e) {
                    return null;
                }

            }

            return positions.isEmpty() ? null : positions;

        } catch (Exception e) {
            return null;
        }
    }
}
