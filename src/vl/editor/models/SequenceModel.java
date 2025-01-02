package vl.editor.models;

import java.util.ArrayList;
import java.util.List;

public class SequenceModel {
    private final List<Note> notes = new ArrayList<>();
    private int instrumentID;

    public SequenceModel() {
        this(0);
    }

    public SequenceModel(int instrumentID) {
        this.instrumentID = instrumentID;
    }

    public void add(Note note) {
        notes.add(note);
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void removeNote(int index) {
        notes.remove(index);
    }

    public Note getNote(int index) {
        return notes.get(index);
    }

    public void setInstrumentID(int instrumentID) {
        this.instrumentID = instrumentID;
    }

    public int getInstrumentID() {
        return instrumentID;
    }
}
