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
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.traccar.storage.QueryIgnore;
import org.traccar.storage.StorageName;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@StorageName("elb_catch_certificate")
public class ElbCatchCertificate extends ElbMessage {

    public ElbCatchCertificate() {
    }

    public ElbCatchCertificate(String protocol) {
        this.protocol = protocol;
    }

    public long getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(long inspectorId) {
        this.inspectorId = inspectorId;
    }

    private long inspectorId;
    private String captainName;
    private String captainPhone;
    private String uniqueNumber;
    private String ownerName;
    private String inspectorName;
    private String inspectorCardId;
    private String ownerPhone;
    private String fishingPermitNumber;
    private Date fishingPermitValidFrom;
    private Date fishingPermitValidTo;

    private String fishingCertificateNumber;
    private Date fishingCertificateValidFrom;
    private Date fishingCertificateValidTo;

    private long landingPortId;
    private long departurePortId;
    private Date landingTime;
    private Date departureTime;
    private boolean outdated = false;
    private boolean valid = true;
    private String protocol;
    private Date serverTime = new Date();
    private Date deviceTime;
    private String tripNumber;
    private Date fixTime;
    private List<Long> geofenceIds;
    private int reasonOfArrival;
    private boolean inspectorVerified = false;
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

    private double speed;
    private double course; // value in meters

    private String landingPortName;
    private String landingPortCode;
    private String landingPortCountry;
    private String departurePortName;
    private String departurePortCountry;
    private String departurePortCode;
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
    public String getLandingPortCode() {
        ElbPorts elbPort = ElbPorts.elbPortsHashMap.getOrDefault(this.landingPortId, new ElbPorts());
        return elbPort.getCode();
    }

    public void setLandingPortCode(String landingPortCode) {
        this.landingPortCode = landingPortCode;
    }

    public String getDeparturePortCode() {
        ElbPorts elbPort = ElbPorts.elbPortsHashMap.getOrDefault(this.departurePortId, new ElbPorts());
        return elbPort.getCode();
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getFishingPermitNumber() {
        return fishingPermitNumber;
    }

    public void setFishingPermitNumber(String fishingPermitNumber) {
        this.fishingPermitNumber = fishingPermitNumber;
    }

    public Date getFishingPermitValidFrom() {
        return fishingPermitValidFrom;
    }

    public void setFishingPermitValidFrom(Date fishingPermitValidFrom) {
        this.fishingPermitValidFrom = fishingPermitValidFrom;
    }

    public Date getFishingPermitValidTo() {
        return fishingPermitValidTo;
    }

    public void setFishingPermitValidTo(Date fishingPermitValidTo) {
        this.fishingPermitValidTo = fishingPermitValidTo;
    }

    public String getFishingCertificateNumber() {
        return fishingCertificateNumber;
    }

    public void setFishingCertificateNumber(String fishingCertificateNumber) {
        this.fishingCertificateNumber = fishingCertificateNumber;
    }

    public Date getFishingCertificateValidFrom() {
        return fishingCertificateValidFrom;
    }

    public void setFishingCertificateValidFrom(Date fishingCertificateValidFrom) {
        this.fishingCertificateValidFrom = fishingCertificateValidFrom;
    }

    public Date getFishingCertificateValidTo() {
        return fishingCertificateValidTo;
    }

    public void setFishingCertificateValidTo(Date fishingCertificateValidTo) {
        this.fishingCertificateValidTo = fishingCertificateValidTo;
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
        ElbPorts elbPort = ElbPorts.elbPortsHashMap.getOrDefault(this.landingPortId, new ElbPorts());
        elbPort.setPortId(landingPortId);
        setLandingPortCode(elbPort.getCode());
        setLandingPortCountry(elbPort.getCountry());
        setLandingPortName(elbPort.getName());

    }
    @QueryIgnore
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
        return elbPort.getCountry();
    }
    @QueryIgnore
    public void setDeparturePortCountry(String departurePortCountry) {

        this.departurePortCountry = departurePortCountry;
    }


    public long getDeparturePortId() {
        return departurePortId;
    }

    public void setDeparturePortId(long departurePortId) {
        this.departurePortId = departurePortId;
        ElbPorts elbPort = ElbPorts.elbPortsHashMap.getOrDefault(this.departurePortId, new ElbPorts());
        elbPort.setPortId(departurePortId);
        setDeparturePortCode(elbPort.getCode());
        setDeparturePortName(elbPort.getName());
        setDeparturePortCountry(elbPort.getCountry());
    }

    public Date getLandingTime() {
        return landingTime;
    }

    public void setLandingTime(Date landingTime) {
        this.landingTime = landingTime;
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


    public void setGeofenceIds(List<? extends Number> geofenceIds) {
        if (geofenceIds != null) {
            this.geofenceIds = geofenceIds.stream().map(Number::longValue).collect(Collectors.toList());
        } else {
            this.geofenceIds = null;
        }
    }
    public boolean getInspectorVerified() {
        return inspectorVerified;
    }
    public void setInspectorVerified(boolean value) {
        this.inspectorVerified = value;
    }
    public boolean getOutdated() {
        return outdated;
    }
    public void setOutdated(boolean outdated) {
        this.outdated = outdated;
    }



    private String fishes;
    private List<ElbCatchCertificateFisheryCatch> catches;
    @JsonIgnore
    public String getFishes() {
        return fishes;
    }

    public void setFishes(String fishes) {
        this.fishes = fishes;
    }
    @QueryIgnore
    public List<ElbCatchCertificateFisheryCatch> getCatches() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        TypeReference<List<ElbCatchCertificateFisheryCatch>> mapType = new TypeReference<>() {
        };
        return objectMapper.readValue(this.fishes, mapType);
    }
    @QueryIgnore
    public void setCatches(List<ElbCatchCertificateFisheryCatch> catches) throws JsonProcessingException {
        this.catches = catches;
        setFishes(new ObjectMapper().writeValueAsString(catches));
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


    public String getInspectorCardId() {
        return inspectorCardId;
    }

    public void setInspectorCardId(String inspectorCardId) {
        this.inspectorCardId = inspectorCardId;
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName;
    }

}
