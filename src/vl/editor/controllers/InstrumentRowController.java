package vl.editor.controllers;

import vl.editor.models.InstrumentRowModel;
import vl.editor.views.InstrumentRowView;

public class InstrumentRowController {
    private final InstrumentRowModel sequence;
    private final InstrumentRowView view;

    public InstrumentRowController(InstrumentRowModel sequence, InstrumentRowView view) {
        this.sequence = sequence;
        this.view = view;
    }

    public InstrumentRowModel getSequence() {
        return sequence;
    }

    public InstrumentRowView getView() {
        return view;
    }
}
