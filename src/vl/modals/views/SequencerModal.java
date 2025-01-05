package vl.modals.views;

import vl.editor.controllers.SequenceController;
import vl.editor.models.Note;
import vl.editor.views.RulerPanel;
import vl.modals.controllers.SequencerModalNotesController;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

public class SequencerModal extends JFrame {
    private final SequenceController sequenceController;
    private final RulerPanel rulerPanel;

    private Function<Note, Void> onNoteAdd;
    private Function<Note, Void> onNoteRemove;

    public SequencerModal(int ticks, SequenceController sequenceController) {
        super("Sequencer");
        setLayout(new BorderLayout());
        setSize(1200, 800);
        setLocationRelativeTo(null);

        this.sequenceController = sequenceController;
        SequencerModalNotesController sequencerModalNotesController = new SequencerModalNotesController(this, ticks);
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

    public void setTickCount(int tickCount) {
        rulerPanel.setTotalTicks(tickCount);
        rulerPanel.setPixelsPerTick(getWidth() / tickCount);
        rulerPanel.repaint();
    }

    public void setOnNoteAdd(Function<Note, Void> onNoteAdd) {
        this.onNoteAdd = onNoteAdd;
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

    public SequenceController getSequenceController() {
        return sequenceController;
    }
}