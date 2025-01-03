package vl.editor.controllers;

import vl.editor.models.EditorModel;
import vl.editor.views.EditorViewPanel;

import java.util.List;

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

    public EditorViewPanel getView() {
        return editorViewPanel;
    }

    public List<InstrumentRowController> getInstrumentRows() {
        return editorModel.getInstrumentRows();
    }
}
