package vl.editor.models;

import vl.editor.controllers.SequenceController;
import vl.util.Tuple;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InstrumentRowModel {
    private final List<Tuple<SequenceController, Integer>> sequences = new ArrayList<>();
    private int instrumentID;

    public InstrumentRowModel() {
        this(0);
    }

    public InstrumentRowModel(int instrumentID) {
        this.instrumentID = instrumentID;
    }

    public SequenceController getSequence(int index) {
        return sequences.get(index).first();
    }

    public void addSequence(SequenceController sequence, Integer tickOffset) {
        sequences.add(new Tuple<>(sequence, tickOffset));

    }

    public List<Tuple<SequenceController, Integer>> getSequences() {
        return sequences;
    }

    public void setInstrumentID(int instrumentID) {
        this.instrumentID = instrumentID;
        sequences.forEach(item -> item.first().setInstrumentID(instrumentID));
    }

    public void setSequencesNoteColor(Color c) {
        sequences.forEach(item -> item.first().setNoteColor(c));
    }

    public void setSequencesBackgroundColor(Color c) {
        sequences.forEach(item -> item.first().setBackgroundColor(c));
    }

    public int getStartOf(SequenceController controller) {
        for (Tuple<SequenceController, Integer> sequence : sequences) {
            if (sequence.first() == controller) {
                return sequence.second();
            }
        }

        return -1;
    }

    public String serialiseToFile() {
        StringBuilder str = new StringBuilder();
        str.append(STR."\{sequences.size()},");
        for (Tuple<SequenceController, Integer> sequence : sequences) {
            str.append(STR."\{sequence.second()},\{sequence.first().serialiseToFile()}");
        }

        return str.toString();
    }

    public int getInstrumentID() {
        return instrumentID;
    }
}
