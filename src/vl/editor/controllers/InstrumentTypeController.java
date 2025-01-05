package vl.editor.controllers;

import vl.editor.models.InstrumentTypeModel;
import vl.editor.views.InstrumentTypeView;

import java.awt.*;

public class InstrumentTypeController {
    private InstrumentTypeModel model;
    private InstrumentTypeView view;
    private InstrumentRowController instrumentRowController;

    public InstrumentTypeController(InstrumentTypeModel model, InstrumentTypeView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public InstrumentTypeController() {
        this(new InstrumentTypeModel(0, "Instrument Type", 100), new InstrumentTypeView());
    }

    public InstrumentTypeModel getModel() {
        return model;
    }

    public void setModel(InstrumentTypeModel model) {
        this.model = model;
    }

    public InstrumentTypeView getView() {
        return view;
    }

    public void setView(InstrumentTypeView view) {
        this.view = view;
    }

    public String getInstrumentTypeName() {
        return model.getInstrumentTypeName();
    }

    public void setInstrumentTypeName(String s) {
        model.setInstrumentTypeName(s);
    }

    public Color getNoteColor() {
        return view.getNoteColor();
    }

    public void setNoteColor(Color color) {
        view.setNoteColor(color);
        if (instrumentRowController != null) {
            instrumentRowController.updateNoteColor(color);
        }

        view.repaint();
    }

    public Color getBackgroundColor() {
        return view.getBackgroundColor();
    }

    public void setBackgroundColor(Color color) {
        view.setBackgroundColor(color);
        if (instrumentRowController != null) {
            instrumentRowController.updateBackgroundColor(color);
        }

        view.repaint();
    }

    public double getVolume() {
        return model.getVolume();
    }

    public void setVolume(int v) {
        model.setVolume(v);
        view.setVolume(v);

        if (instrumentRowController != null)
            instrumentRowController.setVolume(v);
    }

    public InstrumentRowController getInstrumentRowController() {
        return instrumentRowController;
    }

    public void setInstrumentRowController(InstrumentRowController instrumentRowController) {
        this.instrumentRowController = instrumentRowController;
    }

    public void setInstrumentID(int id) {
        model.setInstrumentID(id);

        if (instrumentRowController != null)
            instrumentRowController.setInstrumentID(id);
    }

    public String serialiseToFile() {
        return model.serialiseToFile() + ',' + instrumentRowController.serialiseToFile();
    }
}
