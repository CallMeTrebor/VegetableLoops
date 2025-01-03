package vl.modals.controllers;

import vl.modals.SequencerModalNotesModel;
import vl.modals.views.SequencerModalNotesPanel;

public class SequencerModalNotesController {
    private final SequencerModalNotesPanel view;
    private final SequencerModalNotesModel model;

    public SequencerModalNotesController(SequencerModalNotesModel model, SequencerModalNotesPanel view) {
        this.view = view;
        this.model = model;
    }

    public SequencerModalNotesController() {
        this(new SequencerModalNotesModel(), new SequencerModalNotesPanel());
    }

    public SequencerModalNotesPanel getView() {
        return view;
    }

    public SequencerModalNotesModel getModel() {
        return model;
    }

    public void setTickCount(int tickCount) {
        view.setTickCount(tickCount);
    }
}
