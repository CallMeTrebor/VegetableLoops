package vl.editor.views;

import vl.common.VLConstants;
import vl.editor.controllers.InstrumentRowController;
import vl.editor.controllers.SequenceController;
import vl.util.Tuple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class InstrumentRowView extends JPanel {
    private static final Color SELECTION_COLOR = new Color(255, 0, 0, 100); // Semi-transparent red
    private InstrumentRowController controller;
    private Tuple<Integer, Integer> dragStart;
    private Tuple<Integer, Integer> dragEnd;
    private final int gridCellWidth = 20;

    public InstrumentRowView() {
        setLayout(null);
        setBounds(0, 0, 800, 100);
        setBackground(VLConstants.BACKGROUND_COLOR);
        revalidate();
        repaint();

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dragStart = fromAbsToGridPos(e.getX());
                dragEnd = dragStart;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (dragStart != null && dragEnd != null) {
                    int startTick = Math.min(dragStart.getFirst(), dragEnd.getFirst());
                    int endTick = Math.max(dragStart.getFirst(), dragEnd.getFirst());

                    // Launch modal with a new sequence
                    int tickNumber = Math.max(Math.abs(endTick - startTick), 1);
                    SequenceController sequenceController = new SequenceController(tickNumber, getController());
                    sequenceController.setInstrumentID(0); // TODO: DEBUG NUMBER
                    sequenceController.launchModal(tickNumber);

                    // Add to model
                    controller.addSequence(sequenceController, startTick);
                    add(sequenceController.getView());
                    sequenceController.getView().setBounds(startTick * gridCellWidth, 0, tickNumber * gridCellWidth, getHeight());
                    sequenceController.setOnNoteChanged(note -> {
                        sequenceController.getView().revalidate();
                        sequenceController.getView().repaint();
                        return null;
                    });

                    revalidate();
                    repaint();
                }
                dragStart = null;
                dragEnd = null;
                repaint();
            }
        });

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                dragEnd = fromAbsToGridPos(e.getX());
                repaint();
            }
        });
    }

    public void addSequence(SequenceController sequence, Integer tickOffset) {
        add(sequence.getView());
        sequence.getView().setBounds(tickOffset * gridCellWidth, 0, sequence.getTicks() * gridCellWidth, getHeight());
        sequence.setOnNoteChanged(_ -> {
            sequence.getView().revalidate();
            sequence.getView().repaint();
            return null;
        });

        revalidate();
        repaint();
    }

    private Tuple<Integer, Integer> fromAbsToGridPos(int x) {
        return new Tuple<>(x / gridCellWidth, 0); // Only x matters for tick
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw drag selection
        if (dragStart != null && dragEnd != null) {
            int startX = Math.min(dragStart.getFirst(), dragEnd.getFirst()) * gridCellWidth;
            int endX = Math.max(dragStart.getFirst(), dragEnd.getFirst()) * gridCellWidth;

            g.setColor(SELECTION_COLOR);
            g.fillRect(startX, 0, endX - startX, getHeight());
        }

        // Draw separator line
        g.setColor(VLConstants.BUTTON_COLOR);
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }

    public InstrumentRowController getController() {
        return this.controller;
    }

    public void setController(InstrumentRowController controller) {
        this.controller = controller;
    }

}