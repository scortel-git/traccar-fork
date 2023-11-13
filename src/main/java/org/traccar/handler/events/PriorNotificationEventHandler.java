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
import org.traccar.model.Event;
import org.traccar.model.Position;
import org.traccar.model.PriorNotification;
import org.traccar.session.ConnectionManager;
import org.traccar.session.cache.CacheManager;
import org.traccar.storage.Storage;
import org.traccar.storage.StorageException;
import org.traccar.storage.query.Columns;
import org.traccar.storage.query.Condition;
import org.traccar.storage.query.Order;
import org.traccar.storage.query.Request;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Singleton
@ChannelHandler.Sharable
public class PriorNotificationEventHandler extends BaseEventHandler {

    @Inject
    protected Storage storage;
    @Inject
    protected ConnectionManager connectionManager;

    private final CacheManager cacheManager;
    private final boolean ignoreDuplicateAlerts;

    protected PriorNotificationEventHandler priorNotification;


    @Inject
    public PriorNotificationEventHandler(Config config, CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        ignoreDuplicateAlerts = config.getBoolean(Keys.EVENT_IGNORE_DUPLICATE_ALERTS);
    }

    @Override
    protected Map<Event, Position> analyzePosition(Position position) {
        Object alarm = position.getAttributes().get(Position.KEY_EVENT);
        if (alarm == Position.KEY_PRIOR_NOTIFICATION) {
            try {
                savePriorNotification(position);
            } catch (StorageException e) {
                throw new RuntimeException(e);
            }


        }
        if (alarm != null) {
            boolean ignoreAlert = false;
            if (ignoreDuplicateAlerts) {
                PriorNotification lastPosition = cacheManager.getPriorNotification(position.getPriorNotification().getDeviceId());
                if (lastPosition != null && alarm.equals(lastPosition.getAttributes().get(Position.KEY_ALARM))) {
                    ignoreAlert = true;
                }
            }
            if (!ignoreAlert) {
                Event event = new Event(Event.ALL_EVENTS, position);
                event.set(Position.KEY_EVENT, (String) alarm);
                return Collections.singletonMap(event, position);
            }
        }
        return null;
    }
    protected Map<Event, PriorNotification> analyzePriorNotification(PriorNotification priorNotification) {

        return null;
    }

    public void savePriorNotification(Position position) throws StorageException {

        PriorNotification priorNotification = position.getPriorNotification();
        priorNotification.setDeviceId(position.getDeviceId());
//        priorNotification.setAltitude(position.getAltitude());
//        priorNotification.setCourse(position.getCourse());
//        priorNotification.setTime(position.getDeviceTime());
//        priorNotification.setValid(position.getValid());
//        priorNotification.setOutdated(position.getOutdated());
        priorNotification.set("positionId", position.getId());


        priorNotification.setId(storage.addObject(priorNotification, new Request(new Columns.Exclude("id"))));
        connectionManager.updatePriorNotification(true, priorNotification);
    }

}
