package vl.models;

import java.awt.*;

public class InstrumentTypeModel {
    private int instrumentTypeId;
    private String instrumentTypeName;
    private double volume;
    private Color color;

    public InstrumentTypeModel(int instrumentTypeId, String instrumentTypeName, double volume) {
        this.instrumentTypeId = instrumentTypeId;
        this.instrumentTypeName = instrumentTypeName;
        this.volume = volume;
    }

    public int getInstrumentTypeId() {
        return instrumentTypeId;
    }

    public String getInstrumentTypeName() {
        return instrumentTypeName;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setInstrumentTypeId(int instrumentTypeId) {
        this.instrumentTypeId = instrumentTypeId;
    }

    public void setInstrumentTypeName(String instrumentTypeName) {
        this.instrumentTypeName = instrumentTypeName;
    }
}
