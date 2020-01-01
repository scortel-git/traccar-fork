/*
 * Copyright 2017 Anton Tananaev (anton@traccar.org)
 * Copyright 2017 Andrey Kunitsyn (andrey@traccar.org)
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
package org.traccar.helper;

import java.beans.Introspector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.model.BaseModel;

public final class LogAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAction.class);

    private LogAction() {
    }

    private static final String ACTION_CREATE = "create";
    private static final String ACTION_EDIT = "edit";
    private static final String ACTION_REMOVE = "remove";

    private static final String ACTION_LINK = "link";
    private static final String ACTION_UNLINK = "unlink";

    private static final String ACTION_LOGIN = "login";
    private static final String ACTION_LOGOUT = "logout";
    private static final String ACTION_FAILED_LOGIN_NO_IP = "Failed Login Attempt. IP address: failed to retrieve";

    private static final String ACTION_DEVICE_ACCUMULATORS = "resetDeviceAccumulators";

    private static final String PATTERN_OBJECT = "user: %d, action: %s, object: %s, id: %d";
    private static final String PATTERN_LINK = "user: %d, action: %s, owner: %s, id: %d, property: %s, id: %d";
    private static final String PATTERN_LOGIN = "user: %d, action: %s";
    private static final String PATTERN_FAILED_LOGIN = "Failed Login Attempt. IP address: %s";
    private static final String PATTERN_DEVICE_ACCUMULATORS = "user: %d, action: %s, deviceId: %d";

    public static void create(long userId, BaseModel object) {
        logObjectAction(ACTION_CREATE, userId, object.getClass(), object.getId());
    }

    public static void edit(long userId, BaseModel object) {
        logObjectAction(ACTION_EDIT, userId, object.getClass(), object.getId());
    }

    public static void remove(long userId, Class<?> clazz, long objectId) {
        logObjectAction(ACTION_REMOVE, userId, clazz, objectId);
    }

    public static void link(long userId, Class<?> owner, long ownerId, Class<?> property, long propertyId) {
        logLinkAction(ACTION_LINK, userId, owner, ownerId, property, propertyId);
    }

    public static void unlink(long userId, Class<?> owner, long ownerId, Class<?> property, long propertyId) {
        logLinkAction(ACTION_UNLINK, userId, owner, ownerId, property, propertyId);
    }

    public static void login(long userId) {
        logLoginAction(ACTION_LOGIN, userId);
    }

    public static void logout(long userId) {
        logLoginAction(ACTION_LOGOUT, userId);
    }

    public static void failedLogin(String ipAddress) {

        if(ipAddress == null || ipAddress.isEmpty()) {
            LOGGER.info(ACTION_FAILED_LOGIN_NO_IP);
            }

        else{
            LOGGER.info(String.format(
                    PATTERN_FAILED_LOGIN, ipAddress));
        }

    }


    public static void resetDeviceAccumulators(long userId, long deviceId) {
        LOGGER.info(String.format(
                PATTERN_DEVICE_ACCUMULATORS, userId, ACTION_DEVICE_ACCUMULATORS, deviceId));
    }

    private static void logObjectAction(String action, long userId, Class<?> clazz, long objectId) {
        LOGGER.info(String.format(
                PATTERN_OBJECT, userId, action, Introspector.decapitalize(clazz.getSimpleName()), objectId));
    }

    private static void logLinkAction(String action, long userId,
            Class<?> owner, long ownerId, Class<?> property, long propertyId) {
        LOGGER.info(String.format(
                PATTERN_LINK, userId, action,
                Introspector.decapitalize(owner.getSimpleName()), ownerId,
                Introspector.decapitalize(property.getSimpleName()), propertyId));
    }

    private static void logLoginAction(String action, long userId) {
        LOGGER.info(String.format(PATTERN_LOGIN, userId, action));
    }

}
