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
package org.traccar.model;


import java.util.Date;

public class ElbCrewMember extends ExtendedModel {

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        this.MemberName = memberName;
    }

    public String getMemberEmail() {
        return MemberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.MemberEmail = memberEmail;
    }

    public String getMemberFax() {
        return MemberFax;
    }

    public void setMemberFax(String memberFax) {
        this.MemberFax = memberFax;
    }

    public String getMemberPhone() {
        return MemberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.MemberPhone = memberPhone;
    }

    public String getCrewPosition() {
        return CrewPosition;
    }

    public void setCrewPosition(String crewPosition) {
        this.CrewPosition = crewPosition;
    }

    public String getMemberAddress() {
        return MemberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.MemberAddress = memberAddress;
    }

    public short getZipCode() {
        return zipCode;
    }

    public void setZipCode(short zipCode) {
        this.zipCode = zipCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getNationality() {
        return nationality;
    }

    public void setNationality(int nationality) {
        this.nationality = nationality;
    }

    public String getMedicalInfo() {
        return medicalInfo;
    }

    public void setMedicalInfo(String medicalInfo) {
        this.medicalInfo = medicalInfo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCrationDate() {
        return crationDate;
    }

    public void setCrationDate(Date crationDate) {
        this.crationDate = crationDate;
    }

    public Date getDiscardedCrationDate() {
        return discardedCrationDate;
    }

    public void setDiscardedCrationDate(Date discardedCrationDate) {
        this.discardedCrationDate = discardedCrationDate;
    }

    private String MemberName = "N/A";
    private String MemberEmail = "N/A";
    private String MemberFax = "N/A";
    private String MemberPhone = "N/A";
    private String CrewPosition = "N/A";
    private String MemberAddress = "N/A";
    private short zipCode = -1;
    private String userName = "N/A";
    private String userPassword = "N/A";
    private int nationality = -1;
    private String medicalInfo = "N/A";
    private String notes = "N/A";
    private Date crationDate;
    private Date discardedCrationDate;


    private int type = -1;

    public int getType() {
        return type;
    }

    public void setType(int value) {
        this.type = value;
    }



}
