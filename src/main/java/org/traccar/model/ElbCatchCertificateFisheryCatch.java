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

public class ElbCatchCertificateFisheryCatch extends ExtendedModel {

    public boolean isAdditionalCatch() {
        return isAdditionalCatch;
    }

    public void setAdditionalCatch(boolean additionalCatch) {
        isAdditionalCatch = additionalCatch;
    }

    public boolean isBelowRegularSize() {
        return isBelowRegularSize;
    }

    public void setBelowRegularSize(boolean belowRegularSize) {
        isBelowRegularSize = belowRegularSize;
    }

    public boolean isIncludedDiscardedData() {
        return isIncludedDiscardedData;
    }

    public void setIncludedDiscardedData(boolean includedDiscardedData) {
        isIncludedDiscardedData = includedDiscardedData;
    }

    public boolean isIncludedDiscardedDataCreationTime() {
        return isIncludedDiscardedDataCreationTime;
    }

    public void setIncludedDiscardedDataCreationTime(boolean includedDiscardedDataCreationTime) {
        isIncludedDiscardedDataCreationTime = includedDiscardedDataCreationTime;
    }

    public boolean isDataCorrection() {
        return isDataCorrection;
    }

    public void setDataCorrection(boolean dataCorrection) {
        isDataCorrection = dataCorrection;
    }

    public boolean isProposeProcessing() {
        return isProposeProcessing;
    }

    public void setProposeProcessing(boolean proposeProcessing) {
        isProposeProcessing = proposeProcessing;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public Date getStartFishingOperationDateTime() {
        return startFishingOperationDateTime;
    }

    public void setStartFishingOperationDateTime(Date startFishingOperationDateTime) {
        this.startFishingOperationDateTime = startFishingOperationDateTime;
    }

    public double getStartFishingOperationLatitude() {
        return startFishingOperationLatitude;
    }

    public void setStartFishingOperationLatitude(double startFishingOperationLatitude) {
        if (startFishingOperationLatitude < -90 || startFishingOperationLatitude > 90) {
            throw new IllegalArgumentException("Latitude out of range");
        }
        this.startFishingOperationLatitude = startFishingOperationLatitude;
    }

    public double getStartFishingOperationLongitude() {
        return startFishingOperationLongitude;
    }

    public void setStartFishingOperationLongitude(double startFishingOperationLongitude) {
        if (startFishingOperationLongitude < -180 || startFishingOperationLongitude > 180) {
            throw new IllegalArgumentException("Longitude out of range");
        }
        this.startFishingOperationLongitude = startFishingOperationLongitude;
    }

    public double getStartFishingOperationAltitude() {
        return startFishingOperationAltitude;
    }

    public void setStartFishingOperationAltitude(double startFishingOperationAltitude) {
        this.startFishingOperationAltitude = startFishingOperationAltitude;
    }

    public double getEndFishingOperationLatitude() {
        return endFishingOperationLatitude;
    }

    public void setEndFishingOperationLatitude(double endFishingOperationLatitude) {
        if (endFishingOperationLatitude < -90 || endFishingOperationLatitude > 90) {
            throw new IllegalArgumentException("Latitude out of range");
        }
        this.endFishingOperationLatitude = endFishingOperationLatitude;
    }

    public double getEndFishingOperationLongitude() {
        return endFishingOperationLongitude;
    }

    public void setEndFishingOperationLongitude(double endFishingOperationLongitude) {
        if (endFishingOperationLongitude < -180 || endFishingOperationLongitude > 180) {
            throw new IllegalArgumentException("Longitude out of range");
        }
        this.endFishingOperationLongitude = endFishingOperationLongitude;
    }

    public double getEndFishingOperationAltitude() {
        return endFishingOperationAltitude;
    }

    public void setEndFishingOperationAltitude(double endFishingOperationAltitude) {
        this.endFishingOperationAltitude = endFishingOperationAltitude;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(short speciesId) {

        this.speciesId = speciesId;
        ElbSpeciesExtended elbSpecies = new ElbSpeciesExtended();
        elbSpecies.setElbSpeciesExtended(speciesId);
        setSpeciesCode(elbSpecies.getCode());
        setSpeciesNameBG(elbSpecies.getNameBG());
        setSpeciesNameGB(elbSpecies.getNameGB());

    }
    public int getSpeciesPresentationId() {
        return speciesPresentationId;
    }

    public void setSpeciesPresentationId(int speciesPresentationId) {
        this.speciesPresentationId = speciesPresentationId;
    }

    public int getSpeciesConditionId() {
        return speciesConditionId;
    }

    public void setSpeciesConditionId(int speciesConditionId) {
        this.speciesConditionId = speciesConditionId;
    }

    public int getSpeciesWeightKilograms() {
        return speciesWeightKilograms;
    }

    public void setSpeciesWeightKilograms(int speciesWeightKilograms) {
        this.speciesWeightKilograms = speciesWeightKilograms;
    }

    public short getSpeciesWeightCount() {
        return speciesWeightCount;
    }

    public void setSpeciesWeightCount(short speciesWeightCount) {
        this.speciesWeightCount = speciesWeightCount;
    }
    public int getSpeciesEstimatedWeightKilograms() {
        return speciesEstimatedWeightKilograms;
    }

    public void setSpeciesEstimatedWeightKilograms(int speciesEstimatedWeightKilograms) {
        this.speciesEstimatedWeightKilograms = speciesEstimatedWeightKilograms;
    }

    public short getSpeciesEstimatedWeightCount() {
        return speciesEstimatedWeightCount;
    }

    public void setSpeciesEstimatedWeightCount(short speciesEstimatedWeightCount) {
        this.speciesEstimatedWeightCount = speciesEstimatedWeightCount;
    }
    public short getSpeciesSizeGroupId() {
        return speciesSizeGroupId;
    }

    public void setSpeciesSizeGroupId(short speciesSizeGroupId) {
        this.speciesSizeGroupId = speciesSizeGroupId;
    }

    public int getSpeciesQuantityTypeId() {
        return speciesQuantityTypeId;
    }

    public void setSpeciesQuantityTypeId(int speciesQuantityTypeId) {
        this.speciesQuantityTypeId = speciesQuantityTypeId;
    }

    public int getSpeciesQuantityTypeCount() {
        return speciesQuantityTypeCount;
    }

    public void setSpeciesQuantityTypeCount(int speciesQuantityTypeCount) {
        this.speciesQuantityTypeCount = speciesQuantityTypeCount;
    }

    public Date getEndFishingOperationDateTime() {
        return endFishingOperationDateTime;
    }

    public void setEndFishingOperationDateTime(Date endFishingOperationDateTime) {
        this.endFishingOperationDateTime = endFishingOperationDateTime;
    }
    public String getSpeciesCode() {
        return speciesCode;
    }

    public void setSpeciesCode(String speciesCode) {
        this.speciesCode = speciesCode;
    }

    public String getSpeciesNameBG() {
        return speciesNameBG;
    }

    public void setSpeciesNameBG(String speciesNameBG) {
        this.speciesNameBG = speciesNameBG;
    }

    public String getSpeciesNameGB() {
        return speciesNameGB;
    }

    public void setSpeciesNameGB(String speciesNameGB) {
        this.speciesNameGB = speciesNameGB;
    }

    private String speciesCode;
    private String speciesNameBG;
    private String speciesNameGB;
    private boolean isAdditionalCatch;
    private boolean isBelowRegularSize;
    private boolean isIncludedDiscardedData;
    private boolean isIncludedDiscardedDataCreationTime;
    private boolean isDataCorrection;
    private boolean isProposeProcessing;
    private Date creationDateTime;
    private Date startFishingOperationDateTime;
    private double startFishingOperationLatitude;
    private double startFishingOperationLongitude;
    private double startFishingOperationAltitude;
    private double endFishingOperationLatitude;
    private double endFishingOperationLongitude;
    private double endFishingOperationAltitude;
    private int speciesId;
    private int speciesPresentationId;
    private int speciesConditionId;
    private int speciesWeightKilograms;
    private short speciesWeightCount;
    private int speciesEstimatedWeightKilograms;
    private short speciesEstimatedWeightCount;
    private short speciesSizeGroupId;
    private int speciesQuantityTypeId;
    private int speciesQuantityTypeCount;
    private Date endFishingOperationDateTime;



}
