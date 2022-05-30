/*
 * Copyright 2014 - 2022 Anton Tananaev (anton@traccar.org)
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
package org.traccar.handler;

import io.netty.channel.ChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.BaseDataHandler;
import org.traccar.config.Config;
import org.traccar.config.Keys;
import org.traccar.database.DataManager;
import org.traccar.database.IdentityManager;
import org.traccar.helper.UnitsConverter;
import org.traccar.model.Position;
import org.traccar.storage.StorageException;

import javax.inject.Inject;
import java.util.Date;

@ChannelHandler.Sharable
public class FilterHandler extends BaseDataHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterHandler.class);

    private final boolean enabled;
    private final boolean filterInvalid;
    private final boolean filterZero;
    private final boolean filterDuplicate;
    private final long filterFuture;
    private final boolean filterApproximate;
    private final int filterAccuracy;
    private final boolean filterStatic;
    private final int filterDistance;
    private final int filterMaxSpeed;
    private final long filterMinPeriod;
    private final boolean filterRelative;
    private final long skipLimit;
    private final boolean skipAttributes;

    private final IdentityManager identityManager;
    private final DataManager dataManager;

    @Inject
    public FilterHandler(Config config, IdentityManager identityManager, DataManager dataManager) {
        enabled = config.getBoolean(Keys.FILTER_ENABLE);
        filterInvalid = config.getBoolean(Keys.FILTER_INVALID);
        filterZero = config.getBoolean(Keys.FILTER_ZERO);
        filterDuplicate = config.getBoolean(Keys.FILTER_DUPLICATE);
        filterFuture = config.getLong(Keys.FILTER_FUTURE) * 1000;
        filterAccuracy = config.getInteger(Keys.FILTER_ACCURACY);
        filterApproximate = config.getBoolean(Keys.FILTER_APPROXIMATE);
        filterStatic = config.getBoolean(Keys.FILTER_STATIC);
        filterDistance = config.getInteger(Keys.FILTER_DISTANCE);
        filterMaxSpeed = config.getInteger(Keys.FILTER_MAX_SPEED);
        filterMinPeriod = config.getInteger(Keys.FILTER_MIN_PERIOD) * 1000L;
        filterRelative = config.getBoolean(Keys.FILTER_RELATIVE);
        skipLimit = config.getLong(Keys.FILTER_SKIP_LIMIT) * 1000;
        skipAttributes = config.getBoolean(Keys.FILTER_SKIP_ATTRIBUTES_ENABLE);
        this.identityManager = identityManager;
        this.dataManager = dataManager;
    }

    private boolean filterInvalid(Position position) {
        return filterInvalid && (!position.getValid()
                || position.getLatitude() > 90 || position.getLongitude() > 180
                || position.getLatitude() < -90 || position.getLongitude() < -180);
    }

    private boolean filterZero(Position position) {
        return filterZero && position.getLatitude() == 0.0 && position.getLongitude() == 0.0;
    }

    private boolean filterDuplicate(Position position, Position last) {
        if (filterDuplicate && last != null && position.getFixTime().equals(last.getFixTime())) {
            for (String key : position.getAttributes().keySet()) {
                if (!last.getAttributes().containsKey(key)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean filterFuture(Position position) {
        return filterFuture != 0 && position.getFixTime().getTime() > System.currentTimeMillis() + filterFuture;
    }

    private boolean filterAccuracy(Position position) {
        return filterAccuracy != 0 && position.getAccuracy() > filterAccuracy;
    }

    private boolean filterApproximate(Position position) {
        return filterApproximate && position.getBoolean(Position.KEY_APPROXIMATE);
    }

    private boolean filterStatic(Position position) {
        return filterStatic && position.getSpeed() == 0.0;
    }

    private boolean filterDistance(Position position, Position last) {
        if (filterDistance != 0 && last != null) {
            return position.getDouble(Position.KEY_DISTANCE) < filterDistance;
        }
        return false;
    }

    private boolean filterMaxSpeed(Position position, Position last) {
        if (filterMaxSpeed != 0 && last != null) {
            double distance = position.getDouble(Position.KEY_DISTANCE);
            double time = position.getFixTime().getTime() - last.getFixTime().getTime();
            return UnitsConverter.knotsFromMps(distance / (time / 1000)) > filterMaxSpeed;
        }
        return false;
    }

    private boolean filterMinPeriod(Position position, Position last) {
        if (filterMinPeriod != 0 && last != null) {
            long time = position.getFixTime().getTime() - last.getFixTime().getTime();
            return time > 0 && time < filterMinPeriod;
        }
        return false;
    }

    private boolean skipLimit(Position position, Position last) {
        if (skipLimit != 0 && last != null) {
            return (position.getServerTime().getTime() - last.getServerTime().getTime()) > skipLimit;
        }
        return false;
    }

    private boolean skipAttributes(Position position) {
        if (skipAttributes) {
            String attributesString = identityManager.lookupAttributeString(
                    position.getDeviceId(), "filter.skipAttributes", "", false, true);
            for (String attribute : attributesString.split("[ ,]")) {
                if (position.getAttributes().containsKey(attribute)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean filter(Position position) {

        StringBuilder filterType = new StringBuilder();

        // filter out invalid data
        if (filterInvalid(position)) {
            filterType.append("Invalid ");
        }
        if (filterZero(position)) {
            filterType.append("Zero ");
        }
        if (filterFuture(position)) {
            filterType.append("Future ");
        }
        if (filterAccuracy(position)) {
            filterType.append("Accuracy ");
        }
        if (filterApproximate(position)) {
            filterType.append("Approximate ");
        }

        // filter out excessive data
        long deviceId = position.getDeviceId();
        if (filterDuplicate || filterStatic || filterDistance > 0 || filterMaxSpeed > 0 || filterMinPeriod > 0) {
            Position preceding = null;
            if (filterRelative) {
                try {
                    Date newFixTime = position.getFixTime();
                    preceding = dataManager.getPrecedingPosition(deviceId, newFixTime);
                } catch (StorageException e) {
                    LOGGER.warn("Error retrieving preceding position; fallbacking to last received position.", e);
                    preceding = getLastReceivedPosition(deviceId);
                }
            } else {
                preceding = getLastReceivedPosition(deviceId);
            }
            if (filterDuplicate(position, preceding) && !skipLimit(position, preceding) && !skipAttributes(position)) {
                filterType.append("Duplicate ");
            }
            if (filterStatic(position) && !skipLimit(position, preceding) && !skipAttributes(position)) {
                filterType.append("Static ");
            }
            if (filterDistance(position, preceding) && !skipLimit(position, preceding) && !skipAttributes(position)) {
                filterType.append("Distance ");
            }
            if (filterMaxSpeed(position, preceding)) {
                filterType.append("MaxSpeed ");
            }
            if (filterMinPeriod(position, preceding)) {
                filterType.append("MinPeriod ");
            }
        }

        if (filterType.length() > 0) {
            String uniqueId = identityManager.getById(deviceId).getUniqueId();
            LOGGER.info("Position filtered by {}filters from device: {}", filterType, uniqueId);
            return true;
        }

        return false;
    }

    private Position getLastReceivedPosition(long deviceId) {
        return identityManager.getLastPosition(deviceId);
    }

    @Override
    protected Position handlePosition(Position position) {
        if (enabled && filter(position)) {
            return null;
        }
        return position;
    }

}
