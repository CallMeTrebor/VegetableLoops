package vl.editor.models;

import java.awt.*;

public class InstrumentTypeModel {
    private int instrumentTypeId;
    private String instrumentTypeName;
    private int volume;

    public InstrumentTypeModel(int instrumentTypeId, String instrumentTypeName, int volume) {
        this.instrumentTypeId = instrumentTypeId;
        this.instrumentTypeName = instrumentTypeName;
        this.volume = volume;
    }

    public String getInstrumentTypeName() {
        return instrumentTypeName;
    }

    public void setInstrumentTypeName(String instrumentTypeName) {
        this.instrumentTypeName = instrumentTypeName;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setInstrumentID(int instrumentID) {
        this.instrumentTypeId = instrumentID;
    }

    public String serialiseToFile() {
        return STR."\{instrumentTypeId},\{instrumentTypeName},\{volume}";
    }
}
