package vl.modals.controllers;

import vl.editor.models.Note;
import vl.modals.SequencerModalNotesModel;
import vl.modals.views.SequencerModal;
import vl.modals.views.SequencerModalNotesPanel;

import java.util.List;

public class SequencerModalNotesController {
    private final SequencerModal parent;
    private final SequencerModalNotesPanel view;
    private final SequencerModalNotesModel model;

    public SequencerModalNotesController(SequencerModal parent, int ticks) {
        this(new SequencerModalNotesModel(parent.getSequenceController()), new SequencerModalNotesPanel(ticks), parent);
    }

    public SequencerModalNotesController(SequencerModalNotesModel model, SequencerModalNotesPanel view, SequencerModal parent) {
        this.view = view;
        this.model = model;
        this.parent = parent;

        this.view.setController(this);
    }

    public SequencerModalNotesPanel getView() {
        return view;
    }
    public void addNote(Note n) {
        parent.addNote(n);
    }

    public void removeNote(Note n) {
        parent.removeNote(n);
    }

    public int getTicks() {
        return parent.getTicks();
    }

    public List<Note> getNotes() {
        return model.getNotes();
    }
}
