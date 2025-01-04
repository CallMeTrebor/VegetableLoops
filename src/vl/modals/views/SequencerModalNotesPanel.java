package vl.modals.views;

import vl.common.VLConstants;
import vl.editor.models.Note;
import vl.modals.controllers.SequencerModalNotesController;
import vl.util.Tuple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SequencerModalNotesPanel extends JPanel {
    private int gridCellWidth;
    private int gridCellHeight = 20;

    private Tuple<Integer, Integer> dragStart;
    private Tuple<Integer, Integer> dragEnd;

    private final List<CellToPaint> cellsToPaint = new ArrayList<>();
    private SequencerModalNotesController controller;


    public SequencerModalNotesPanel(int ticks) {
        setLayout(null);
        setSize(800, 600);
        setBackground(VLConstants.BACKGROUND_COLOR);

        gridCellWidth = getWidth() / ticks;

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dragStart = fromAbsToGridPos(e.getX(), e.getY());
                dragEnd = dragStart; // Initialize dragEnd to the starting point
                repaint(); // Trigger a repaint to show the initial cell
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (dragStart != null && dragEnd != null) {
                    addCellsToPaint(dragStart, dragEnd);
                }
                dragStart = null;
                dragEnd = null;
                repaint(); // Final repaint to ensure all cells are shown
            }
        });

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                dragEnd = fromAbsToGridPos(e.getX(), e.getY());
                repaint(); // Repaint in real time during dragging
            }
        });
    }

    private Tuple<Integer, Integer> fromAbsToGridPos(int x, int y) {
        return new Tuple<>(x / gridCellWidth, y / gridCellHeight);
    }

    private void addCellsToPaint(Tuple<Integer, Integer> start, Tuple<Integer, Integer> end) {
        int startX = Math.max(0, Math.min(start.getFirst(), end.getFirst()));
        int endX = Math.min(controller.getTicks() - 1, Math.max(start.getFirst(), end.getFirst()));
        int startY = Math.max(0, Math.min(start.getSecond(), end.getSecond()));
        int endY = Math.max(startY, startY);

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                cellsToPaint.add(new CellToPaint(x, y));
            }
        }

        // Add note to the controller
        Note note = new Note(startY, 100, Math.max(1, endX - startX + 1), Math.min(startX, endX));
        controller.addNote(note);
    }

    public void setController(SequencerModalNotesController controller) {
        this.controller = controller;
    }

    public SequencerModalNotesController getController() {
        return controller;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gridCellWidth = getWidth() / controller.getTicks();

        // Paint all finalized cells
        for (CellToPaint cell : cellsToPaint) {
            if (cell.x >= 0 && cell.x < controller.getTicks()) {
                cell.paint(g);
            }
        }

        // Paint currently dragged area
        if (dragStart != null && dragEnd != null) {
            int startX = Math.max(0, Math.min(dragStart.getFirst(), dragEnd.getFirst())); // Clamp to grid bounds
            int endX = Math.min(controller.getTicks() - 1, Math.max(dragStart.getFirst(), dragEnd.getFirst())); // Clamp to grid bounds
            int y = Math.max(0, dragStart.getSecond()); // Ensure Y is non-negative

            g.setColor(Color.RED);
            for (int x = startX; x <= endX; x++) {
                g.fillRect(x * gridCellWidth, y * gridCellHeight, gridCellWidth, gridCellHeight);
            }
        }
    }

    private class CellToPaint {
        int x, y;

        public CellToPaint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void paint(Graphics g) {
            g.setColor(Color.BLUE); // finalized cells are painted blue for debugging
            g.fillRect(x * gridCellWidth, y * gridCellHeight, gridCellWidth, gridCellHeight);
        }
    }

    public int getGridCellWidth() {
        return gridCellWidth;
    }

    public void setGridCellWidth(int gridCellWidth) {
        this.gridCellWidth = gridCellWidth;
    }

    public int getGridCellHeight() {
        return gridCellHeight;
    }

    public void setGridCellHeight(int gridCellHeight) {
        this.gridCellHeight = gridCellHeight;
    }
}