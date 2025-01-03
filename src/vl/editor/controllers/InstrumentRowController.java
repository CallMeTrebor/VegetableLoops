package vl.editor.controllers;

import vl.editor.models.InstrumentRowModel;
import vl.editor.views.InstrumentRowView;
import vl.util.Tuple;

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
    }
}
