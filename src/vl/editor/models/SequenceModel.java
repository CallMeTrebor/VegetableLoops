package vl.editor.models;

import java.util.ArrayList;
import java.util.List;

public class SequenceModel {
    private final List<Note> notes = new ArrayList<>();
    private int instrumentID;
    private int ticks;

    public SequenceModel() {
        this(0, 1);
    }

    public SequenceModel(int instrumentID, int ticks) {
        this.instrumentID = instrumentID;
        this.ticks = ticks;
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

    public void removeNote(Note note) {
        notes.remove(note);
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

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }
}
