package vl.modals.views;
import vl.editor.controllers.SequenceController;
import vl.editor.views.RulerPanel;
import vl.modals.controllers.SequencerModalNotesController;

import javax.swing.*;
import java.awt.*;

public class SequencerModal extends JFrame {
    private SequenceController sequenceController;
    private static final int GRID_ROWS = 12; // Number of note rows
    private static final int GRID_COLS = 64; // Number of time ticks
    private SequencerModalNotesController sequencerModalNotesController;
    private RulerPanel rulerPanel;

    public SequencerModal() {
        super("Sequencer");
        setLayout(new BorderLayout());
        setSize(1200, 400);
        setLocationRelativeTo(null);

        sequencerModalNotesController = new SequencerModalNotesController();
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
        sequencerModalNotesController.setTickCount(tickCount);
        rulerPanel.setTotalTicks(tickCount);
        rulerPanel.setPixelsPerTick(getWidth() / tickCount);
        rulerPanel.repaint();
    }
}