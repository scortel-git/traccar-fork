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
            } catch (StorageException e) {
                throw new RuntimeException(e);
            }


        } else if (alarm == Position.KEY_END_FISHING_TRIP) {
            try {
                handleEndFishingTripNotification(position);
            } catch (StorageException e) {
                throw new RuntimeException(e);
            }
        } else if (alarm == Position.KEY_LANDING_DECLARATION) {
            try {
                handleLandingDeclarationCertificate(position);
            } catch (StorageException e) {
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
                Event event = new Event(Event.TYPE_ELB_MESSAGE, position);
                event.set(Position.KEY_EVENT, (String) alarm);
                event.set("priorId", position.getElbObject().getId());
                return Collections.singletonMap(event, position);
            }
        }
        return null;
    }

    protected Map<Event, ElbMessage> analyzePosition(ElbMessage elbMessage) {

        return null;
    }



    public void savePriorNotification(Position position) throws StorageException {

        Object object = position.getElbObject();
        if (object instanceof PriorNotification) {
            PriorNotification priorNotification = (PriorNotification) object;
            priorNotification.setDeviceId(position.getDeviceId());


            priorNotification.set("positionId", position.getId());
            priorNotification.setId(storage.addObject(priorNotification, new Request(new Columns.Exclude("id"))));
            connectionManager.updatePriorNotification(true, priorNotification);


        }
    }

    public void handleLandingDeclarationCertificate(Position position) throws StorageException {
        ElbLandingDeclaration entity = (ElbLandingDeclaration) position.getElbObject();
        entity.setDeviceId(position.getDeviceId());
        entity.setPositionId(position.getId());
        entity.setPositionId(position.getId());
        Device device = cacheManager.getObject(Device.class, position.getDeviceId());
        entity.setDriverId(ElbUtil.getDriverId(storage));

        boolean isDuplicated = false;

        ElbEndFishingTrip elbEndFishingTrip = ElbUtil.lookupEndFishingTrip(storage, entity.getTripNumber());
        if (elbEndFishingTrip != null) {

        }
        if (device == null) {
            device = ElbUtil.getDeviceByIdStorage(storage, position.getDeviceId());
        }
        entity.setDeviceId(device.getId());
        try {
            entity.setId(storage.addObject(entity, new Request(new Columns.Exclude("id"))));

        } catch (Exception ignore) {
        } finally {
            connectionManager.updateElbEntity(true, entity);
        }
    }


        public void handleEndFishingTripNotification(Position position) throws StorageException {
        ElbMessage entity = position.getElbObject();
        entity.setDeviceId(position.getDeviceId());
        entity.setPositionId(position.getId());
        Device device = cacheManager.getObject(Device.class, position.getDeviceId());
        int counter = 1;
        boolean isDuplicated = false;


        if (device == null) {
            device = ElbUtil.getDeviceByIdStorage(storage, position.getDeviceId());
        }
        entity.setDeviceId(device.getId());
        try {
            if (entity instanceof ElbEndFishingTrip) {
                List<ElbEndFishingTrip> oldElbEndFishingTrips = ElbUtil.getOldEndFishingTrips(storage, ((ElbEndFishingTrip) entity).getTripNumber());
                if (!oldElbEndFishingTrips.isEmpty()) {
                    isDuplicated = ElbUtil.getIsDuplicated(oldElbEndFishingTrips, (ElbEndFishingTrip) entity);
                    if (!isDuplicated) {
                        ElbEndFishingTrip previous = ElbUtil.handlePreviousEndFishingTrips(storage, oldElbEndFishingTrips, (ElbEndFishingTrip) entity);
                        ((ElbEndFishingTrip) entity).setUniqueNumber(previous.getUniqueNumber());
                    }

                } else {
                    String uniqueNumber = !Objects.equals(
                            device.getAttributes().getOrDefault("cfr", "").toString(), "") ?
                            device.getAttributes().get("cfr").toString() :
                            device.getUniqueId();

                    long elbEndFishingTripCounts = ElbUtil.getCountElbMessages(storage, entity, device.getId());


                    ((ElbEndFishingTrip) entity).setUniqueNumber(
                            uniqueNumber + "-" + String.format(
                                    "%02d",
                                    elbEndFishingTripCounts > 0 ?
                                            elbEndFishingTripCounts + counter
                                            : counter));

                }
                if(isDuplicated){
                    position.setValid(false);
                    position.set("duplicated", "true");
                }else {
                    entity.setDriverId(ElbUtil.getDriverId(storage));
                    try {
                        entity.setId(storage.addObject(entity, new Request(new Columns.Exclude("id"))));

                    } catch (Exception ignore) {
                    } finally {
                        connectionManager.updateElbEntity(true, entity);
                    }
                }
            }
        }catch (Exception ignore){
        }
    }

}
