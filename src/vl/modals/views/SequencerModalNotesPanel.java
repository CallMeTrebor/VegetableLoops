package vl.modals.views;

import vl.common.VLConstants;
import vl.util.Tuple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SequencerModalNotesPanel extends JPanel {
    private int gridCellWidth = 20; // Increased size for better visibility
    private int gridCellHeight = 20;

    private Tuple<Integer, Integer> dragStart;
    private Tuple<Integer, Integer> dragEnd;

    private final List<CellToPaint> cellsToPaint = new ArrayList<>();

    public SequencerModalNotesPanel() {
        setLayout(null);
        setSize(800, 600);
        setBackground(VLConstants.BACKGROUND_COLOR);

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
        int startX = Math.min(start.getFirst(), end.getFirst());
        int endX = Math.max(start.getFirst(), end.getFirst());
        int startY = Math.min(start.getSecond(), start.getSecond());
        int endY = Math.max(start.getSecond(), start.getSecond());

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                cellsToPaint.add(new CellToPaint(x, y));
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Paint all finalized cells
        for (CellToPaint cell : cellsToPaint) {
            cell.paint(g);
        }

        // Paint currently dragged area
        if (dragStart != null && dragEnd != null) {
            int startX = Math.min(dragStart.getFirst(), dragEnd.getFirst());
            int endX = Math.max(dragStart.getFirst(), dragEnd.getFirst());
            int y = dragStart.getSecond();

            g.setColor(Color.RED);
            for (int x = startX; x <= endX; x++) {
                g.fillRect(x * gridCellWidth, y * gridCellHeight, gridCellWidth, gridCellHeight);
            }
        }
    }

    public void setTickCount(int tickCount) {
        gridCellWidth = getWidth() / tickCount;
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