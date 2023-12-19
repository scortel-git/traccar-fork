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
import org.traccar.storage.QueryIgnore;
import org.traccar.storage.StorageName;

import java.util.Date;

@StorageName("elb_errors")
public class ElbMessageErrors extends ElbMessage {

    public ElbMessageErrors() {
    }

    public ElbMessageErrors(String protocol) {
        this.protocol = protocol;
    }

    private String protocol;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Date getServerTime() {
        return serverTime;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }



    @Override
    @JsonIgnore
    @QueryIgnore
    public long getCaptainId() {
        return captainId;
    }

    @Override
    @JsonIgnore
    @QueryIgnore
    public void setCaptainId(long captainId) {
        this.captainId = captainId;
    }

    @Override
    @JsonIgnore
    @QueryIgnore
    public double getLatitude() {
        return latitude;
    }

    @Override
    @JsonIgnore
    @QueryIgnore
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    @JsonIgnore
    @QueryIgnore
    public double getLongitude() {
        return longitude;
    }

    @Override
    @JsonIgnore
    @QueryIgnore
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    @JsonIgnore
    @QueryIgnore
    public double getAltitude() {
        return altitude;
    }

    @Override
    @JsonIgnore
    @QueryIgnore
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    @Override
    @JsonIgnore
    @QueryIgnore
    public String getType() {
        return type;
    }

    @Override
    @JsonIgnore
    @QueryIgnore
    public void setType(String type) {
        this.type = type;
    }

    @Override
    @JsonIgnore
    @QueryIgnore
    public Date getFixTime() {
        return fixTime;
    }

    @Override
    @JsonIgnore
    @QueryIgnore
    public void setFixTime(Date fixTime) {
        this.fixTime = fixTime;
    }

    private Date fixTime;
    private long captainId;
    private double latitude;
    private double longitude;
    private double altitude;
    private String type;
    private Date serverTime = new Date();
    private Long messageId;
}
