/*
 * Copyright 2012 - 2022 Anton Tananaev (anton@traccar.org)
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
package org.traccar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.traccar.storage.QueryIgnore;
import org.traccar.storage.StorageName;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@StorageName("elb_start_fishing_trip")
public class ElbStartFishingTrip extends ElbMessage {

    public ElbStartFishingTrip() {
    }

    public ElbStartFishingTrip(String protocol) {
        this.protocol = protocol;
    }

    private String protocol;
    private Date serverTime = new Date();
    private Date deviceTime;
    private Date fixTime;
    private boolean outdated;
    private boolean valid;
    private double latitude;
    private double longitude;
    private double altitude; // value in meters
    private Network network;
    private List<Long> geofenceIds;
    private List<Long> fishIds;
    @JsonIgnore
    @QueryIgnore
    public List<Object> getCatches() {
        return catches;
    }
    @JsonIgnore
    @QueryIgnore
    public void setCatches(List<Object> catches) throws JsonProcessingException {

        this.catches = catches;
        setFishes(new ObjectMapper().writeValueAsString(catches));

    }

    public String getFishes() {
        return fishes;
    }

    public void setFishes(String fishes) {

        this.fishes = fishes;
    }

    private String fishes;
    private List<Object> catches;
    private String tripNumber;
    public String getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(String tripNumber) {
        this.tripNumber = tripNumber;
    }
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    public Date getServerTime() {
        return serverTime;
    }
    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }
    public Date getDeviceTime() {
        return deviceTime;
    }
    public void setDeviceTime(Date deviceTime) {
        this.deviceTime = deviceTime;
    }
    public Date getFixTime() {
        return fixTime;
    }
    public void setFixTime(Date fixTime) {
        this.fixTime = fixTime;
    }

    @QueryIgnore
    public void setTime(Date time) {
        setDeviceTime(time);
        setFixTime(time);
    }


    @QueryIgnore
    public boolean getOutdated() {
        return outdated;
    }

    @QueryIgnore
    public void setOutdated(boolean outdated) {
        this.outdated = outdated;
    }


    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude out of range");
        }
        this.latitude = latitude;
    }


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude out of range");
        }
        this.longitude = longitude;
    }


    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }


    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }


    public List<Long> getGeofenceIds() {
        return geofenceIds;
    }
    public List<Long> fishIds() {
        return fishIds;
    }

    public void setGeofenceIds(List<? extends Number> geofenceIds) {
        if (geofenceIds != null) {
            this.geofenceIds = geofenceIds.stream().map(Number::longValue).collect(Collectors.toList());
        } else {
            this.geofenceIds = null;
        }
    }
    public void setFishIds(List<? extends Number> fishIds) {
        if (fishIds != null) {
            this.fishIds = fishIds.stream().map(Number::longValue).collect(Collectors.toList());
        } else {
            this.fishIds = null;
        }
    }
    private double speed;
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    private  double course;
    public double getCourse() {
        return course;
    }

    public void setCourse(double course) {
        this.course = course;
    }

    private long departurePortId;
    public long getDeparturePortId() {
        return this.departurePortId;
    }

    public void setDeparturePortId(long departurePortId) {
        this.departurePortId = departurePortId;
    }

    private String departurePortCode;
    public String getDeparturePortCode() {
        return this.departurePortCode;
    }

    public void setDeparturePortCode(String departurePortCode) {
        this.departurePortCode = departurePortCode;
    }

    @JsonIgnore
    @QueryIgnore
    @Override
    public String getType() {
        return super.getType();
    }

    @JsonIgnore
    @QueryIgnore
    @Override
    public void setType(String type) {
        super.setType(type);
    }

}
