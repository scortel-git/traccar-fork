/*
 * Copyright 2015 - 2019 Anton Tananaev (anton@traccar.org)
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
package org.traccar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandler;
import org.traccar.config.Config;
import org.traccar.config.Keys;
import org.traccar.database.IdentityManager;
import org.traccar.helper.Checksum;
import org.traccar.model.Device;
import org.traccar.model.Position;
import org.traccar.model.Group;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.InvocationCallback;
import java.util.HashMap;
import java.util.Map;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.BufferUtils;
import org.apache.commons.collections.buffer.CircularFifoBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class WebDataHandler extends BaseDataHandler {

    private static final String KEY_POSITION = "position";
    private static final String KEY_DEVICE = "device";

    private static final Logger LOGGER = LoggerFactory.getLogger(WebDataHandler.class);

    private final IdentityManager identityManager;
    private final ObjectMapper objectMapper;
    private final Client client;

    private final String url;
    private final String header;
    private final boolean json;

    private final Integer queueSize;
    private final Integer queueConcurrency;

    private Buffer positionsQueue = null;
    private AtomicInteger pendingRequests;

    @Inject
    public WebDataHandler(
            Config config, IdentityManager identityManager, ObjectMapper objectMapper, Client client) {

        this.identityManager = identityManager;
        this.objectMapper = objectMapper;
        this.client = client;
        this.url = config.getString(Keys.FORWARD_URL);
        this.header = config.getString(Keys.FORWARD_HEADER);
        this.json = config.getBoolean(Keys.FORWARD_JSON);

        Integer queueSize = (config.getInteger(Keys.FORWARD_QUEUE_SIZE, 0));
        this.queueSize = (queueSize > 0) ? queueSize : 0;

        Integer queueConcurrency = (config.getInteger(Keys.FORWARD_QUEUE_CONCURRENCY, 1));
        this.queueConcurrency = (queueConcurrency > 0) ? queueConcurrency : 1;

        if (this.queueSize > 0) {
            this.positionsQueue = BufferUtils.synchronizedBuffer(new CircularFifoBuffer(this.queueSize));
        }

        this.pendingRequests = new AtomicInteger(0);
    }

    private static String formatSentence(Position position) {

        StringBuilder s = new StringBuilder("$GPRMC,");

        try (Formatter f = new Formatter(s, Locale.ENGLISH)) {

            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.ENGLISH);
            calendar.setTimeInMillis(position.getFixTime().getTime());

            f.format("%1$tH%1$tM%1$tS.%1$tL,A,", calendar);

            double lat = position.getLatitude();
            double lon = position.getLongitude();

            f.format("%02d%07.4f,%c,", (int) Math.abs(lat), Math.abs(lat) % 1 * 60, lat < 0 ? 'S' : 'N');
            f.format("%03d%07.4f,%c,", (int) Math.abs(lon), Math.abs(lon) % 1 * 60, lon < 0 ? 'W' : 'E');

            f.format("%.2f,%.2f,", position.getSpeed(), position.getCourse());
            f.format("%1$td%1$tm%1$ty,,", calendar);
        }

        s.append(Checksum.nmea(s.toString()));

        return s.toString();
    }

    private String calculateStatus(Position position) {
        if (position.getAttributes().containsKey(Position.KEY_ALARM)) {
            return "0xF841"; // STATUS_PANIC_ON
        } else if (position.getSpeed() < 1.0) {
            return "0xF020"; // STATUS_LOCATION
        } else {
            return "0xF11C"; // STATUS_MOTION_MOVING
        }
    }

    public String formatRequest(Position position) throws UnsupportedEncodingException, JsonProcessingException {

        Device device = identityManager.getById(position.getDeviceId());

        String request = url
                .replace("{name}", URLEncoder.encode(device.getName(), StandardCharsets.UTF_8.name()))
                .replace("{uniqueId}", device.getUniqueId())
                .replace("{status}", device.getStatus())
                .replace("{deviceId}", String.valueOf(position.getDeviceId()))
                .replace("{protocol}", String.valueOf(position.getProtocol()))
                .replace("{deviceTime}", String.valueOf(position.getDeviceTime().getTime()))
                .replace("{fixTime}", String.valueOf(position.getFixTime().getTime()))
                .replace("{valid}", String.valueOf(position.getValid()))
                .replace("{latitude}", String.valueOf(position.getLatitude()))
                .replace("{longitude}", String.valueOf(position.getLongitude()))
                .replace("{altitude}", String.valueOf(position.getAltitude()))
                .replace("{speed}", String.valueOf(position.getSpeed()))
                .replace("{course}", String.valueOf(position.getCourse()))
                .replace("{accuracy}", String.valueOf(position.getAccuracy()))
                .replace("{statusCode}", calculateStatus(position));

        if (position.getAddress() != null) {
            request = request.replace(
                    "{address}", URLEncoder.encode(position.getAddress(), StandardCharsets.UTF_8.name()));
        }

        if (request.contains("{attributes}")) {
            String attributes = objectMapper.writeValueAsString(position.getAttributes());
            request = request.replace(
                    "{attributes}", URLEncoder.encode(attributes, StandardCharsets.UTF_8.name()));
        }

        if (request.contains("{gprmc}")) {
            request = request.replace("{gprmc}", formatSentence(position));
        }

        if (request.contains("{group}")) {
            String deviceGroupName = "";
            if (device.getGroupId() != 0) {
                Group group = Context.getGroupsManager().getById(device.getGroupId());
                if (group != null) {
                    deviceGroupName = group.getName();
                }
            }

            request = request.replace("{group}", URLEncoder.encode(deviceGroupName, StandardCharsets.UTF_8.name()));
        }

        return request;
    }

    @Override
    protected Position handlePosition(Position position) {

        class AsyncRequestAndCallback implements InvocationCallback<Response> {

            private Map<String, Object> jsonPayload;
            private Invocation.Builder requestBuilder;

            AsyncRequestAndCallback(Position position) {

                String formattedUrl;
                try {
                    formattedUrl = (json) ? url : formatRequest(position);
                } catch (UnsupportedEncodingException | JsonProcessingException e) {
                    throw new RuntimeException("Forwarding formatting error", e);
                }

                requestBuilder = client.target(formattedUrl).request();
                if (header != null && !header.isEmpty()) {
                    for (String line: header.split("\\r?\\n")) {
                        String[] values = line.split(":", 2);
                        requestBuilder.header(values[0].trim(), values[1].trim());
                    }
                }

                if (json) {
                    jsonPayload = prepareJsonPayload(position);
                }

                pendingRequests.incrementAndGet();

                send();
            }

            private void send() {
                if (json) {
                    requestBuilder.async().post(Entity.json(jsonPayload), this);
                } else {
                    requestBuilder.async().get(this);
                }
            }

            private void retry() {
                try {
                    Thread.sleep(1000);
                    send();
                } catch (Exception e) {
                }
            }

            private void next() {
                if (!positionsQueue.isEmpty()) {
                    new AsyncRequestAndCallback((Position) positionsQueue.remove());
                }
            }

            public void completed(Response response) {
                if (positionsQueue != null) {
                    boolean ok = (response.getStatus() == 200);
                    boolean retry = (positionsQueue.size() < queueSize);
                    if (!ok && retry) {
                        retry();
                    } else {
                        pendingRequests.decrementAndGet();
                        while (!positionsQueue.isEmpty() && pendingRequests.get() < queueConcurrency) {
                            next();
                        }
                    }
                }
            }

            public void failed(Throwable throwable) {
                if (positionsQueue != null) {
                    if (positionsQueue.size() < queueSize) {
                        retry();
                    } else if (pendingRequests.decrementAndGet() == 0) {
                        next();
                    }
                }
            }

        }

        if (positionsQueue == null || pendingRequests.get() == 0) {
            new AsyncRequestAndCallback(position);
        } else {
            positionsQueue.add(position);
        }

        logQueueStatus();

        return position;
    }

    private void logQueueStatus() {
        LOGGER.info(String.format("Position forwarding queue: %d/%d/%d",
            pendingRequests.get(), positionsQueue.size(), queueSize));
    }

    private Map<String, Object> prepareJsonPayload(Position position) {

        Map<String, Object> data = new HashMap<>();
        Device device = identityManager.getById(position.getDeviceId());

        data.put(KEY_POSITION, position);

        if (device != null) {
            data.put(KEY_DEVICE, device);
        }

        return data;
    }

}
