package org.traccar.model;

public class ElbSpeciesExtended extends ExtendedModel {
    private String nameBG = "NA";
    public String getNameBG() {
        return nameBG;
    }
    public void setNameBG(String value) {
        this.nameBG = value;
    }
    private String nameGB = "NA";
    public String getNameGB() {
        return nameGB;
    }
    public void setNameGB(String value) {
        nameGB = value;
    }
    private int type = -1;
    public int getType() {
        return type;
    }
    public void setType(int i) {
        type = i;
    }
    private String code = "NA";
    public String getCode() {
        return code;
    }
    public void setCode(String str) {
        code = str.trim().toUpperCase();
    }
    private String presentation = "NA";
    public void setPresentation(String presentation) {
        this.presentation = presentation.trim().toUpperCase();
    }
    public String getPresentation() {
        return presentation;
    }
    public short getSequence() {
        return sequence;
    }

    public void setSequence(short sequence) {
        this.sequence = sequence;
    }

    public short sequence;


    public void setElbSpeciesExtended(short sequence) {

        ElbSpecies species = new ElbSpecies().getSpecies(sequence);
        setSequence(sequence);
        setCode(species.getCode());
        setNameBG(species.getNameBG());
        setNameGB(species.getNameGB());
        setPresentation(species.getPresentation());
        setType(species.getType());

    }


}
