package vl.editor.controllers;

import vl.editor.models.InstrumentRowModel;
import vl.editor.views.InstrumentRowView;
import vl.util.Tuple;

import javax.sound.midi.Track;
import java.awt.*;
import java.util.List;

public class InstrumentRowController {
    private final InstrumentRowModel model;
    private final InstrumentRowView view;

    public InstrumentRowController() {
        this(new InstrumentRowModel(), new InstrumentRowView());
    }

    public InstrumentRowController(InstrumentRowModel model, InstrumentRowView view) {
        this.model = model;
        this.view = view;

        this.view.setController(this);
    }

    public int getStart(SequenceController sequence) {
        return model.getStartOf(sequence);
    }

    public InstrumentRowModel getModel() {
        return model;
    }

    public InstrumentRowView getView() {
        return view;
    }

    public List<Tuple<SequenceController, Integer>> getSequences() {
        return model.getSequences();
    }

    public void addSequence(SequenceController sequence, Integer tickOffset) {
        this.model.addSequence(sequence, tickOffset);
        sequence.setNoteColor(model.getSequence(0).getNoteColor());
        sequence.setBackgroundColor(model.getSequence(0).getBackgroundColor());

        view.addSequence(sequence, tickOffset);
        view.revalidate();
        view.repaint();
    }

    public void compileToTrack(Track track) {
        Thread[] threads = new Thread[model.getSequences().size()];
        int sequenceNum = model.getSequences().size();
        for (int i = 0; i < sequenceNum; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                SequenceController sequence = model.getSequence(finalI);
                sequence.compileToTrack(track, model.getSequences().get(finalI).getSecond());
            });
            threads[i].start();
        }

        // wait until all threads are finished
        for (int i = 0; i < sequenceNum; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateNoteColor(Color c) {
        model.setSequencesNoteColor(c);
    }

    public void updateBackgroundColor(Color c) {
        model.setSequencesBackgroundColor(c);
    }

    public void setInstrumentID(int instrumentID) {
        model.setInstrumentID(instrumentID);
    }

    public void removeSequence(SequenceController sequence) {
        model.getSequences().removeIf(item -> item.getFirst() == sequence);
        view.remove(sequence.getView());
        view.revalidate();
        view.repaint();
    }

    public String serialiseToFile() {
        return model.serialiseToFile();
    }

    public void setVolume(int v) {
        model.getSequences().forEach(item -> item.getFirst().setVolume(v));
    }
}
