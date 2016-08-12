/*
 * Copyright 2016 Anton Tananaev (anton.tananaev@gmail.com)
 * Copyright 2016 Andrey Kunitsyn (abyss@fox5.ru)
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
package org.traccar.reports.model;

public class SummaryReport {

    private long deviceId;
    public long getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    private String deviceName;
    public String getDeviceName() {
        return deviceName;
    }
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    private double distance;
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void addDistance(double distance) {
        this.distance += distance;
    }

    private double averageSpeed;
    public double getAverageSpeed() {
        return averageSpeed;
    }
    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    private double maxSpeed;
    public double getMaxSpeed() {
        return maxSpeed;
    }
    public void setMaxSpeed(double maxSpeed) {
        if (maxSpeed > this.maxSpeed) {
            this.maxSpeed = maxSpeed;
        }
    }

    private long engineHours;
    public long getEngineHours() {
        return engineHours;
    }
    public void setEngineHours(long engineHours) {
        this.engineHours = engineHours;
    }
    public void addEngineHours(long engineHours) {
        this.engineHours += engineHours;
    }
}
