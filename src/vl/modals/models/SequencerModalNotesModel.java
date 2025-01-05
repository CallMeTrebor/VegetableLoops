package vl.modals.models;

import vl.editor.controllers.SequenceController;
import vl.editor.models.Note;

import java.util.List;

public class SequencerModalNotesModel {
    private final SequenceController sequenceController;

    public SequencerModalNotesModel(SequenceController sequenceController) {
        this.sequenceController = sequenceController;
    }

    public List<Note> getNotes() {
        return sequenceController.getNotes();
    }

    public void removeNote(Note note) {
        sequenceController.removeNote(note);
    }
}
