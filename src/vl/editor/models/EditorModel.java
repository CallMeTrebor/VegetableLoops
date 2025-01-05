package vl.editor.models;

import vl.editor.controllers.InstrumentRowController;

import java.util.ArrayList;
import java.util.List;

public class EditorModel {
    private final List<InstrumentRowController> instrumentRows = new ArrayList<>();

    public EditorModel() {
    }

    public List<InstrumentRowController> getInstrumentRows() {
        return instrumentRows;
    }

    public void addInstrumentRow(InstrumentRowController instrumentRow) {
        instrumentRows.add(instrumentRow);
    }

    public void removeInstrumentRow(InstrumentRowController instrumentRow) {
        instrumentRows.remove(instrumentRow);
    }

    public void reorderLines(List<Integer> reorderList) {
        List<InstrumentRowController> newInstrumentRows = new ArrayList<>();
        for (int i : reorderList) {
            newInstrumentRows.add(instrumentRows.get(i));
        }
        instrumentRows.clear();
        instrumentRows.addAll(newInstrumentRows);
    }
}
