/*
 * Copyright 2020 - 2022 Anton Tananaev (anton@traccar.org)
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
package org.traccar.schedule;

import org.traccar.LifecycleObject;

import javax.inject.Singleton;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Singleton
public class ScheduleManager implements LifecycleObject {

    private ScheduledExecutorService executor;

    @Override
    public void start() {
        executor = Executors.newSingleThreadScheduledExecutor();

        new TaskDeviceInactivityCheck().schedule(executor);
        new TaskWebSocketKeepalive().schedule(executor);
        new TaskHealthCheck().schedule(executor);
    }

    @Override
    public void stop() {
        if (executor != null) {
            executor.shutdown();
            executor = null;
        }
    }

}
