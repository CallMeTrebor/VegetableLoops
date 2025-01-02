package vl.editor.models;

import vl.editor.controllers.SequenceController;
import vl.util.Tuple;

import java.util.ArrayList;
import java.util.List;

public class InstrumentRowModel {
    private int instrumentID;
    private final List<Tuple<SequenceController, Integer>> sequences = new ArrayList<>();

    public InstrumentRowModel() {
        this(0);
    }

    public InstrumentRowModel(int instrumentID) {
        this.instrumentID = instrumentID;
    }

    public SequenceController getSequence(int index) {
        return sequences.get(index).getFirst();
    }

    public void addSequence(SequenceController sequence, Integer tickOffset) {
        sequences.add(new Tuple<>(sequence, tickOffset));
    }

    public List<Tuple<SequenceController, Integer>> getSequences() {
        return sequences;
    }

    public int getInstrumentID() {
        return instrumentID;
    }

    public void setInstrumentID(int instrumentID) {
        this.instrumentID = instrumentID;
    }
}
