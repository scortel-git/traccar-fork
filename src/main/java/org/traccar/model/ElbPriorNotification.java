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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.traccar.storage.QueryIgnore;
import org.traccar.storage.StorageName;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@StorageName("elb_prior_notification")
public class ElbPriorNotification extends ElbMessage {

    public ElbPriorNotification() {
    }

    public ElbPriorNotification(String protocol) {
        this.protocol = protocol;
    }

    private String captainName;
    private String captainPhone;
    private long landingPortId;
    private long departurePortId;
    private String landingPortCode;
    private String departurePortCode;
    private Date estimatedTimeOfArrival;
    private Date departureTime;
    private boolean outdated = false;
    private boolean valid = true;
    private boolean isCancellation = false;
    private String protocol;
    private Date serverTime = new Date();
    private Date deviceTime;
    private String tripNumber;
    private Date fixTime;
    private List<Long> geofenceIds;
    private int reasonOfArrival;
    private double speed;
    private double course; // value in meters


    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getCourse() {
        return course;
    }

    public void setCourse(double course) {
        this.course = course;
    }

    public String getCaptainName() {
        return captainName;
    }
    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public String getCaptainPhone() {
        return captainPhone;
    }

    public void setCaptainPhone(String captainPhone) {
        this.captainPhone = captainPhone;
    }

    public long getLandingPortId() {
        return landingPortId;
    }

    public void setLandingPortId(long landingPortId) {
        this.landingPortId = landingPortId;
    }

    public long getDeparturePortId() {
        return departurePortId;
    }

    public void setDeparturePortId(long departurePortId) {
        this.departurePortId = departurePortId;
    }

    public Date getEstimatedTimeOfArrival() {
        return estimatedTimeOfArrival;
    }

    public void setEstimatedTimeOfArrival(Date estimatedTimeOfArrival) {
        this.estimatedTimeOfArrival = estimatedTimeOfArrival;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }
    public int getReasonOfArrival() {
        return reasonOfArrival;
    }
    public void setReasonOfArrival(int reasonOfArrival) {
        this.reasonOfArrival = reasonOfArrival;
    }

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
    public boolean getValid() {
        return valid;
    }
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    public List<Long> getGeofenceIds() {
        return geofenceIds;
    }


    private String fishes;
    private List<ElbPriorNotificationFisheryCatch> catches;
    @JsonIgnore
    public String getFishes() {
        return fishes;
    }
    public void setFishes(String fishes) {
        this.fishes = fishes;
    }
    @QueryIgnore
    public List<ElbPriorNotificationFisheryCatch> getCatches() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        TypeReference<List<ElbPriorNotificationFisheryCatch>> mapType = new TypeReference<>() {
        };
        return objectMapper.readValue(this.fishes, mapType);
    }
    @QueryIgnore
    public void setCatches(List<ElbPriorNotificationFisheryCatch> catches) throws JsonProcessingException {
        this.catches = catches;
        setFishes(new ObjectMapper().writeValueAsString(catches));
    }

    public void setGeofenceIds(List<? extends Number> geofenceIds) {
        if (geofenceIds != null) {
            this.geofenceIds = geofenceIds.stream().map(Number::longValue).collect(Collectors.toList());
        } else {
            this.geofenceIds = null;
        }
    }
    private String landingPortName;
    private String landingPortCountry;
    private String departurePortName;
    private String departurePortCountry;

    public void setLandingPortCode(String landingPortCode) {
        this.landingPortCode = landingPortCode;
    }
    @QueryIgnore
    public String getLandingPortCode() {
        return landingPortCode;
    }
    @QueryIgnore
    public String getLandingPortName() {
        ElbPorts elbPort = ElbPorts.elbPortsHashMap.getOrDefault(this.landingPortId, new ElbPorts());
        return elbPort.getName();
    }
    @QueryIgnore
    public void setLandingPortName(String landingPortName) {
        this.landingPortName = landingPortName;
    }
    @QueryIgnore
    public String getLandingPortCountry() {
        ElbPorts elbPort = ElbPorts.elbPortsHashMap.getOrDefault(this.landingPortId, new ElbPorts());
        return elbPort.getCountry();
    }
    @QueryIgnore
    public void setLandingPortCountry(String landingPortCountry) {
        this.landingPortCountry = landingPortCountry;
    }
    @QueryIgnore
    public String getDeparturePortCode() {
        ElbPorts elbPort = ElbPorts.elbPortsHashMap.getOrDefault(this.departurePortId, new ElbPorts());
        return elbPort.getCode();
    }
    public void setDeparturePortCode(String departurePortCode) {
        this.departurePortCode = departurePortCode;
    }
    @QueryIgnore
    public String getDeparturePortName() {
        ElbPorts elbPort = ElbPorts.elbPortsHashMap.getOrDefault(this.departurePortId, new ElbPorts());
        return elbPort.getName();
    }
    @QueryIgnore
    public void setDeparturePortName(String departurePortName) {
        this.departurePortName = departurePortName;
    }
    @QueryIgnore
    public String getDeparturePortCountry() {
        ElbPorts elbPort = ElbPorts.elbPortsHashMap.getOrDefault(this.departurePortId, new ElbPorts());
        return elbPort.getCountry();    }
    @QueryIgnore
    public void setDeparturePortCountry(String departurePortCountry) {
        this.departurePortCountry = departurePortCountry;
    }

    public boolean isCancellation() {
        return isCancellation;
    }

    public void setCancellation(boolean cancellation) {
        isCancellation = cancellation;
    }

    @QueryIgnore
    public boolean getOutdated() {
        return outdated;
    }
    @QueryIgnore
    public void setOutdated(boolean outdated) {
        this.outdated = outdated;
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
