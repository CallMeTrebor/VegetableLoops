package vl.controllers;

import vl.models.EditorModel;
import vl.views.EditorViewPanel;

public class EditorController {
    private final EditorModel editorModel;
    private final EditorViewPanel editorViewPanel;

    public EditorController(EditorModel editorModel, EditorViewPanel editorViewPanel) {
        this.editorModel = editorModel;
        this.editorViewPanel = editorViewPanel;

        editorModel.getInstrumentRows().forEach(item -> editorViewPanel.addView(item.getView()));
        editorViewPanel.repaint();
    }

    public EditorModel getEditorModel() {
        return editorModel;
    }

    public EditorViewPanel getEditorViewPanel() {
        return editorViewPanel;
    }

    public void addInstrumentRow(InstrumentRowController instrumentRowController) {
        editorModel.addInstrumentRow(instrumentRowController);
        editorViewPanel.addView(instrumentRowController.getView());
        editorViewPanel.repaint();
    }

    public void removeInstrumentRow(InstrumentRowController instrumentRowController) {
        editorModel.removeInstrumentRow(instrumentRowController);
        editorViewPanel.removeView(instrumentRowController.getView());
        editorViewPanel.repaint();
    }

    public void clearInstrumentRows() {
        editorModel.getInstrumentRows().clear();
        editorViewPanel.clearView();
        editorViewPanel.repaint();
    }
}
