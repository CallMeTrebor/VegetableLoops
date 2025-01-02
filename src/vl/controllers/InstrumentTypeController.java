package vl.controllers;

import vl.models.InstrumentTypeModel;
import vl.views.InstrumentTypeView;

public class InstrumentTypeController {
    private InstrumentTypeModel model;
    private InstrumentTypeView view;

    public InstrumentTypeController(InstrumentTypeModel model, InstrumentTypeView view) {
        this.model = model;
        this.view = view;
    }

    public InstrumentTypeModel getModel() {
        return model;
    }

    public InstrumentTypeView getView() {
        return view;
    }

    public void setModel(InstrumentTypeModel model) {
        this.model = model;
    }

    public void setView(InstrumentTypeView view) {
        this.view = view;
    }

    public String getInstrumentTypeName() {
        return model.getInstrumentTypeName();
    }
}
