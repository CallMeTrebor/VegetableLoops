package vl.editor.controllers;

import vl.editor.models.EditorModel;
import vl.editor.views.EditorViewPanel;

import javax.sound.midi.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EditorController {
    private final EditorModel editorModel;
    private final EditorViewPanel editorViewPanel;
    private Sequencer sequencer;

    public EditorController(EditorModel editorModel, EditorViewPanel editorViewPanel) {
        this.editorModel = editorModel;
        this.editorViewPanel = editorViewPanel;

        editorModel.getInstrumentRows().forEach(item -> editorViewPanel.addView(item.getView()));
        editorViewPanel.repaint();
    }

    public void addInstrumentRowToModel(InstrumentRowController instrumentRow) {
        editorModel.addInstrumentRow(instrumentRow);
    }

    public void playMusic() {
        try {
            if (sequencer == null) {
                sequencer = MidiSystem.getSequencer();
                sequencer.open();
            }

            Sequence sequence = new Sequence(Sequence.PPQ, 24);
            Track track = sequence.createTrack();
            compileToTrack(track);

            sequencer.setSequence(sequence);
            sequencer.start();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    public void compileToTrack(Track track) {
        editorModel.getInstrumentRows().forEach(item -> item.compileToTrack(track));
    }

    public void stopMusic() {
        if (sequencer != null && sequencer.isRunning()) {
            sequencer.stop();
        }
    }

    public void saveToFile(String fileToSave, List<InstrumentTypeController> instrumentTypeControllers) {
        String str = instrumentTypeControllers.stream()
                .map(InstrumentTypeController::serialiseToFile)
                .reduce("", (acc, item) -> acc + ("\n" + item));

        try {
            Files.writeString(Path.of(fileToSave), str);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
