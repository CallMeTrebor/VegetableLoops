package vl.modals.views;
import vl.editor.controllers.SequenceController;
import vl.editor.models.Note;
import vl.editor.views.RulerPanel;
import vl.modals.controllers.SequencerModalNotesController;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

public class SequencerModal extends JFrame {
    private SequenceController sequenceController;
    private static final int GRID_ROWS = 12; // Number of note rows
    private static final int GRID_COLS = 64; // Number of time ticks

    private SequencerModalNotesController sequencerModalNotesController;
    private RulerPanel rulerPanel;

    private Function<Note, Void> onNoteAdd;
    private Function<Note, Void> onNoteRemove;

    public SequencerModal(int ticks) {
        super("Sequencer");
        setLayout(new BorderLayout());
        setSize(1200, 400);
        setLocationRelativeTo(null);

        sequencerModalNotesController = new SequencerModalNotesController(this, ticks);
        rulerPanel = new RulerPanel();
        add(rulerPanel, BorderLayout.NORTH);
        add(sequencerModalNotesController.getView(), BorderLayout.CENTER);

        // Handle the modal closing
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (sequenceController != null) sequenceController.onModalClosing();
                dispose();
            }
        });
    }

    public void setSequenceController(SequenceController sequenceController) {
        this.sequenceController = sequenceController;
    }

    public void setTickCount(int tickCount) {
        rulerPanel.setTotalTicks(tickCount);
        rulerPanel.setPixelsPerTick(getWidth() / tickCount);
        rulerPanel.repaint();
    }

    public Function<Note, Void> getOnNoteAdd() {
        return onNoteAdd;
    }

    public void setOnNoteAdd(Function<Note, Void> onNoteAdd) {
        this.onNoteAdd = onNoteAdd;
    }

    public Function<Note, Void> getOnNoteRemove() {
        return onNoteRemove;
    }

    public void setOnNoteRemove(Function<Note, Void> onNoteRemove) {
        this.onNoteRemove = onNoteRemove;
    }

    public void addNote(Note note) {
        if (onNoteAdd != null) onNoteAdd.apply(note);
    }

    public void removeNote(Note note) {
        if (onNoteRemove != null) onNoteRemove.apply(note);
    }

    public int getTicks() {
        return sequenceController.getTicks();
    }
}