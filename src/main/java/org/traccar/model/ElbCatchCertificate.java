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

@StorageName("elb_catch_certificate")
public class ElbCatchCertificate extends ElbMessage {

    public ElbCatchCertificate() {
    }

    public ElbCatchCertificate(String protocol) {
        this.protocol = protocol;
    }

    private String captainName;
    private String captainPhone;
    private String uniqueNumber;
    private String ownerName;
    private String ownerPhone;
    private String fishingPermitNumber;
    private Date fishingPermitValidFrom;
    private Date fishingPermitValidTo;

    private String fishingCertificateNumber;
    private Date fishingCertificateValidFrom;
    private Date fishingCertificateValidTo;

    private short landingPortId;
    private short departurePortId;
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
    private String fishes;
    private List<Object> catches;
    private boolean isInspectorVerified;

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

    public short getLandingPortId() {
        return landingPortId;
    }

    public void setLandingPortId(short landingPortId) {
        this.landingPortId = landingPortId;
    }

    public short getDeparturePortId() {
        return departurePortId;
    }

    public void setDeparturePortId(short departurePortId) {
        this.departurePortId = departurePortId;
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
    public String getFishes() {
        return fishes;
    }
    public void setFishes(String fishes) {

        this.fishes = fishes;
    }

    public void setGeofenceIds(List<? extends Number> geofenceIds) {
        if (geofenceIds != null) {
            this.geofenceIds = geofenceIds.stream().map(Number::longValue).collect(Collectors.toList());
        } else {
            this.geofenceIds = null;
        }
    }
    public boolean isInspectorVerified() {
        return isInspectorVerified;
    }
    public void setInspectorVerified(boolean inspectorVerified) {
        isInspectorVerified = inspectorVerified;
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
    public List<Object> getCatches() {
        return catches;
    }
    @JsonIgnore
    @QueryIgnore
    public void setCatches(List<Object> catches) throws JsonProcessingException {

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

}
