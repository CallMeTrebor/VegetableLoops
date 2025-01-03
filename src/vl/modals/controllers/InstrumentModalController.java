package vl.modals.controllers;

import vl.editor.controllers.InstrumentRowController;
import vl.editor.controllers.InstrumentTypeController;
import vl.editor.models.InstrumentTypeModel;
import vl.modals.views.InstrumentModal;

import java.util.function.Function;

public class InstrumentModalController {
    private InstrumentModal instrumentModal;

    private Function<InstrumentTypeModel, InstrumentTypeController> onModalExit;

    public InstrumentModalController() {
        this(new InstrumentModal());
    }

    public InstrumentModalController(InstrumentModal instrumentModal) {
        this.instrumentModal = instrumentModal;
        instrumentModal.setInstrumentModalController(this);
    }

    public void setOnModalExit(Function<InstrumentTypeModel, InstrumentTypeController> onModalExit) {
        this.onModalExit = onModalExit;
    }

    public void onModalExit(InstrumentTypeModel instrumentTypeModel) {
        instrumentModal.dispose();
        onModalExit.apply(instrumentTypeModel);
    }
}
