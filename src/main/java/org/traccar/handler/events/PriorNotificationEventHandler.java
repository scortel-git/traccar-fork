/*
 * Copyright 2016 - 2022 Anton Tananaev (anton@traccar.org)
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
package org.traccar.handler.events;

import io.netty.channel.ChannelHandler;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.traccar.config.Config;
import org.traccar.config.Keys;
import org.traccar.helper.model.ElbUtil;
import org.traccar.model.*;
import org.traccar.session.ConnectionManager;
import org.traccar.session.cache.CacheManager;
import org.traccar.storage.Storage;
import org.traccar.storage.StorageException;
import org.traccar.storage.query.Columns;
import org.traccar.storage.query.Request;

import java.util.*;

@Singleton
@ChannelHandler.Sharable
public class PriorNotificationEventHandler extends BaseEventHandler {

    @Inject
    protected Storage storage;
    @Inject
    protected ConnectionManager connectionManager;

    private final CacheManager cacheManager;
    private final boolean ignoreDuplicateAlerts;

    protected Object priorNotification;

    private Map<String, Object> eventAttributes = new HashMap<>();


    @Inject
    public PriorNotificationEventHandler(Config config, CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        ignoreDuplicateAlerts = config.getBoolean(Keys.EVENT_IGNORE_DUPLICATE_ALERTS);
    }

    @Override
    protected Map<Event, Position> analyzePosition(Position position) {


        Object alarm = position.getAttributes().get(Position.KEY_EVENT);
        if (alarm == Position.KEY_ELB_NOTIFICATION) {
            try {
                handleEndFishingTripNotification(position);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        } else if (alarm == Position.KEY_END_FISHING_TRIP) {
            try {
                handleEndFishingTripNotification(position);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (alarm == Position.KEY_LANDING_DECLARATION) {
            try {
                handleLandingDeclarationCertificate(position);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (alarm == Position.KEY_START_FISHING_TRIP) {
            try {
                handleStartFishingTrip(position);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        } else if (alarm == Position.KEY_PRIOR_NOTIFICATION) {
            try {
                handlePriorNotification(position);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (alarm == Position.KEY_CATCH_CERTIFICATE) {
            try {
                handleCatchCertificate(position);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (alarm == Position.KEY_PRIOR_NOTIFICATION_CANCELLATION) {
            try {
                position.setValid(false);
                handleCatchCertificate(position);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (alarm != null) {
            boolean ignoreAlert = false;
            if (ignoreDuplicateAlerts) {
                PriorNotification lastPosition = cacheManager.getPriorNotification(position.getDeviceId());
                if (lastPosition != null && alarm.equals(lastPosition.getAttributes().get(Position.KEY_ALARM))) {
                    ignoreAlert = true;
                }
            }
            if (!ignoreAlert && position.getValid()) {
                Event event = new Event(position.getString("event", Event.TYPE_ELB_MESSAGE), position);
                eventAttributes.put(Position.KEY_EVENT, (String) alarm);
                event.setAttributes(eventAttributes);
                event.set(Position.KEY_EVENT, (String) alarm);
                return Collections.singletonMap(event, position);
            }
        }
        return null;
    }

    protected Map<Event, ElbMessage> analyzePosition(ElbMessage elbMessage) {

        return null;
    }

    public void handleLandingDeclarationCertificate(Position position) throws StorageException {
        ElbLandingDeclaration entity = (ElbLandingDeclaration) position.getElbObject();
        entity.setDeviceId(position.getDeviceId());
        entity.setPositionId(position.getId());
        entity.setPositionId(position.getId());
        Device device = cacheManager.getObject(Device.class, position.getDeviceId());
        entity.setCaptainId(ElbUtil.getCaptainId(storage, device));
        ElbStartFishingTrip elbStartFishingTrip = ElbUtil.lookupElbStartFishingTrip(storage, entity.getTripNumber());
        if (elbStartFishingTrip != null) {
            entity.setDeparturePortId(elbStartFishingTrip.getDeparturePortId());
            entity.setLandingPortId(elbStartFishingTrip.getDeparturePortId());
        }
        int counter = 1;
        boolean isDuplicated = false;

        long elbLandingDeclarationCount = ElbUtil.getCountElbMessages(storage, entity, device.getId());


        ElbEndFishingTrip elbEndFishingTrip = ElbUtil.lookupEndFishingTrip(storage, entity.getTripNumber());
        if (elbEndFishingTrip != null) {
            entity.setElbPriorNotificationId(elbEndFishingTrip.getId());

        }
        if (device == null) {
            device = ElbUtil.getDeviceByIdStorage(storage, position.getDeviceId());
        }
        entity.setDeviceId(device.getId());
        entity.setValid(true);
        try {

            List<ElbLandingDeclaration> oldElbLandingDeclaration = ElbUtil.getOldElbLandingDeclaration(storage, entity.getTripNumber());
            if (!oldElbLandingDeclaration.isEmpty()) {
                isDuplicated = ElbUtil.getIsDuplicated(oldElbLandingDeclaration, entity);
                if (!isDuplicated) {
                    ElbLandingDeclaration previous = ElbUtil.handlePreviousElbMessages(storage, oldElbLandingDeclaration, entity);
                    entity.setUniqueNumber(previous.getUniqueNumber());
                }
            } else {
                String uniqueNumber = !Objects.equals(
                        device.getAttributes().getOrDefault("cfr", "").toString(), "") ?
                        device.getAttributes().get("cfr").toString() :
                        device.getUniqueId();

                entity.setUniqueNumber(
                        uniqueNumber + "-" + String.format(
                                "%02d",
                                elbLandingDeclarationCount + counter));

            }
            if (isDuplicated) {
                position.setValid(false);
                position.set("duplicated", "true");
            } else {
                entity.setCaptainId(ElbUtil.getCaptainId(storage, device));
                try {
                    entity.setId(storage.addObject(entity, new Request(new Columns.Exclude("id"))));

                } catch (Exception e) {
                    int error = 5;
                } finally {
                    eventAttributes.put("messageId", entity.getId());
                    connectionManager.updateElbEntity(true, entity);
                }
            }

        } catch (Exception ignore) {
            int i = 0;
        } finally {
            connectionManager.updateElbEntity(true, entity);
        }
    }

    public void handleStartFishingTrip(Position position) throws StorageException {
        ElbStartFishingTrip entity = (ElbStartFishingTrip) position.getElbObject();
        entity.setDeviceId(position.getDeviceId());
        entity.setPositionId(position.getId());
        entity.setPositionId(position.getId());
        Device device = cacheManager.getObject(Device.class, position.getDeviceId());
        entity.setCaptainId(ElbUtil.getCaptainId(storage, device));

        boolean isDuplicated = false;

        if (device == null) {
            device = ElbUtil.getDeviceByIdStorage(storage, position.getDeviceId());
        }
        entity.setDeviceId(device.getId());
        entity.setValid(true);
        try {

            List<ElbStartFishingTrip> oldElbStartFishingTrips = ElbUtil.getOldElbStartFishingTrips(storage, entity.getTripNumber());
            if (!oldElbStartFishingTrips.isEmpty()) {
                isDuplicated = ElbUtil.getIsDuplicated(oldElbStartFishingTrips, entity);
            }

            if (isDuplicated) {
                position.setValid(false);
                position.set("duplicated", "true");
            } else {
                entity.setCaptainId(ElbUtil.getCaptainId(storage, device));
                try {
                    entity.setId(storage.addObject(entity, new Request(new Columns.Exclude("id"))));

                } catch (Exception e) {
                    int error = 3;
                }
            }

        } catch (Exception ignore) {
            int i = 0;
        }
        finally {
            eventAttributes.put("messageId", entity.getId());
            connectionManager.updateElbEntity(true, entity);
        }
    }


    public void handleEndFishingTripNotification(Position position) throws StorageException {
        ElbEndFishingTrip entity = (ElbEndFishingTrip) position.getElbObject();
        entity.setDeviceId(position.getDeviceId());
        entity.setPositionId(position.getId());
        Device device = cacheManager.getObject(Device.class, position.getDeviceId());
        long elbEndFishingTripCounts = ElbUtil.getCountElbMessages(storage, entity, device.getId());

        ElbStartFishingTrip elbStartFishingTrip = ElbUtil.lookupElbStartFishingTrip(storage, entity.getTripNumber());
        entity.setDeparturePortId(elbStartFishingTrip != null ? elbStartFishingTrip.getDeparturePortId() : -1);
        if (entity.getDeparturePortId() > -1) {
            ElbPorts elbPort = ElbPorts.elbPortsHashMap.getOrDefault(entity.getDeparturePortId(), new ElbPorts());
            elbPort.setPortId(entity.getDeparturePortId());
            entity.setDeparturePortCode(elbPort.getCode());
        }


        int counter = 1;
        boolean isDuplicated = false;


        if (device == null) {
            device = ElbUtil.getDeviceByIdStorage(storage, position.getDeviceId());
        }
        entity.setDeviceId(device.getId());
        try {
            List<ElbEndFishingTrip> oldElbEndFishingTrips = ElbUtil.getOldEndFishingTrips(storage, entity.getTripNumber());
            if (!oldElbEndFishingTrips.isEmpty()) {
                isDuplicated = ElbUtil.getIsDuplicated(oldElbEndFishingTrips, entity);
                if (!isDuplicated) {
                    ElbEndFishingTrip previous = ElbUtil.handlePreviousEndFishingTrips(storage, oldElbEndFishingTrips, entity);
                    entity.setUniqueNumber(previous.getUniqueNumber());
                }
            } else {
                String uniqueNumber = !Objects.equals(
                        device.getAttributes().getOrDefault("cfr", "").toString(), "") ?
                        device.getAttributes().get("cfr").toString() :
                        device.getUniqueId();


                entity.setUniqueNumber(
                        uniqueNumber + "-" + String.format(
                                "%02d",
                                elbEndFishingTripCounts > 0 ?
                                        elbEndFishingTripCounts + counter
                                        : counter));

            }
            if (isDuplicated) {
                position.setValid(false);
                position.set("duplicated", "true");
                entity.setCaptainId(ElbUtil.getCaptainId(storage, device));

            } else {
                entity.setCaptainId(ElbUtil.getCaptainId(storage, device));
                String trip = entity.getTripNumber();
                try {
                    entity.setId(storage.addObject(entity, new Request(new Columns.Exclude("id"))));

                } catch (Exception e) {
                    int error = 0;
                } finally {
                    eventAttributes.put("messageId", entity.getId());
                    connectionManager.updateElbEntity(true, entity);
                }
            }

        } catch (Exception e) {
            int error = 1;
        }
    }

    private void handleCatchCertificate(Position position) throws StorageException {
        Map<String, Object> attributes = new HashMap<>();
        int counter = 1;
        boolean isDuplicated = false;
        ElbCatchCertificate entity = (ElbCatchCertificate) position.getElbObject();
        Driver captain = cacheManager.findDriverByUniqueId(position.getDeviceId(), entity.getCaptainPhone());
        if (captain != null) {
            entity.setCaptainId(captain.getId());
            attributes.put("captain", captain);
            entity.setAttributes(attributes);
        }

        entity.setDeviceId(position.getDeviceId());
        entity.setPositionId(position.getId());
        Device device = cacheManager.getObject(Device.class, position.getDeviceId());
        device = device == null ? ElbUtil.getDeviceByIdStorage(storage, position.getDeviceId()) : device;
        long elbCatchCertificateCounts = ElbUtil.getCountElbMessages(storage, entity, device.getId());
        entity.setDeviceId(device.getId());
        try {
            List<ElbCatchCertificate> oldElbCatchCertificate = ElbUtil.getOldElbCatchCertificate(storage, entity.getTripNumber());
            if (!oldElbCatchCertificate.isEmpty()) {
                isDuplicated = ElbUtil.getIsDuplicated(oldElbCatchCertificate, entity);
                if (isDuplicated) {
                    ElbCatchCertificate previous = ElbUtil.handlePreviousElbMessages(storage, oldElbCatchCertificate, entity);
                    entity.setUniqueNumber(previous.getUniqueNumber());
                }
            }
            if (entity.getUniqueNumber() == null) {
                String uniqueNumber = !Objects.equals(
                        device.getAttributes().getOrDefault("cfr", "").toString(), "") ?
                        device.getAttributes().get("cfr").toString() :
                        device.getUniqueId();


                entity.setUniqueNumber(
                        uniqueNumber + "-" + String.format(
                                "%02d",
                                elbCatchCertificateCounts > 0 ?
                                        elbCatchCertificateCounts + counter
                                        : counter));

            }

            entity.setCaptainId(ElbUtil.getCaptainId(storage, device));
            String trip = entity.getTripNumber();
            try {
                entity.setId(storage.addObject(entity, new Request(new Columns.Exclude("id"))));

            } catch (Exception e) {
                int error = 0;
            } finally {
                if (isDuplicated) {
                    position.setValid(false);
                    position.set("duplicated", "true");
                } else {
                    eventAttributes.put("messageId", entity.getId());
                    connectionManager.updateElbEntity(true, entity);
                }
            }


        } catch (Exception e) {
            int error = 1;
        }
    }

    private void handlePriorNotification(Position position) throws StorageException {

        Map<String, Object> attributes = new HashMap<>();
        boolean isDuplicated = false;

        ElbPriorNotification entity = (ElbPriorNotification) position.getElbObject();

        List<ElbPriorNotification> oldElbPriorNotification = ElbUtil.getOldElbPriorNotification(storage, entity.getTripNumber());
        if (!oldElbPriorNotification.isEmpty()) {
            isDuplicated = ElbUtil.getIsDuplicated(oldElbPriorNotification, entity);
        }
        if (isDuplicated) {
            position.setValid(false);
            position.set("duplicated", "true");
        } else {
            Driver captain = cacheManager.findDriverByUniqueId(position.getDeviceId(), entity.getCaptainPhone());
            if (captain != null) {
                entity.setCaptainId(captain.getId());
                attributes.put("captain", captain);
            }
            entity.setPositionId(position.getId());
            Device device = cacheManager.getObject(Device.class, position.getDeviceId());
            device = device == null ? ElbUtil.getDeviceByIdStorage(storage, position.getDeviceId()) : device;
            entity.setDeviceId(device.getId());
            entity.setAttributes(attributes);

            try {
                entity.setId(storage.addObject(entity, new Request(new Columns.Exclude("id"))));
                eventAttributes.put("messageId", entity.getId());
            } catch (Exception e) {
                int error = 0;
            } finally {
                connectionManager.updateElbEntity(true, entity);
            }
        }
    }
}



