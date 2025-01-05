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

public class SequencerModalNotesPanel extends JPanel {
    private final List<CellToPaint> cellsToPaint = new ArrayList<>();
    private int gridCellWidth;
    private final int gridCellHeight = 20;
    private Tuple<Integer, Integer> dragStart;
    private Tuple<Integer, Integer> dragEnd;
    private Tuple<Integer, Integer> hoveredCell; // Track the hovered cell
    private SequencerModalNotesController controller;

    private final int topNote; // MIDI note number for the top note

    public SequencerModalNotesPanel(int ticks, int topNote) {
        this.topNote = topNote;
        initializePanel(ticks);
    }

    // Default constructor with topNote set to C6 (MIDI number 84)
    public SequencerModalNotesPanel(int ticks) {
        this(ticks, 84); // Default to C6
    }

    private void initializePanel(int ticks) {
        setLayout(null);
        setSize(800, 1080);
        setBackground(VLConstants.BACKGROUND_COLOR);

        gridCellWidth = getWidth() / ticks;

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!SwingUtilities.isLeftMouseButton(e)) return;
                dragStart = fromAbsToGridPos(e.getX(), e.getY());
                dragEnd = dragStart; // Initialize dragEnd to the starting point
                repaint(); // Trigger a repaint to show the initial cell
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!SwingUtilities.isLeftMouseButton(e)) return;
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

            @Override
            public void mouseMoved(MouseEvent e) {
                hoveredCell = fromAbsToGridPos(e.getX(), e.getY());
                repaint(); // Trigger repaint to show hover effect
            }
        });

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    Tuple<Integer, Integer> clickedCell = fromAbsToGridPos(e.getX(), e.getY());
                    removeCellToPaint(clickedCell);
                    repaint(); // Repaint after cell deletion
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    dragStart = fromAbsToGridPos(e.getX(), e.getY());
                    dragEnd = dragStart; // Initialize dragEnd to the starting point
                    repaint(); // Trigger a repaint to show the initial cell
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (dragStart != null && dragEnd != null) {
                        addCellsToPaint(dragStart, dragEnd);
                    }
                    dragStart = null;
                    dragEnd = null;
                    repaint(); // Final repaint to ensure all cells are shown
                }
            }
        });
    }

    private void removeCellToPaint(Tuple<Integer, Integer> cell) {
        // Remove the cell from visual representation
        cellsToPaint.removeIf(paintedCell ->
                paintedCell.x == cell.getFirst() && paintedCell.y == cell.getSecond()
        );

        // Remove the corresponding note from the controller
        int midiNote = topNote - cell.getSecond();
        Note noteToRemove = null;

        for (Note note : controller.getNotes()) {
            int noteStartTick = (int) note.getEntryTick();
            int noteEndTick = noteStartTick + note.getDuration();
            if (note.getNote() == midiNote &&
                    noteStartTick <= cell.getFirst() &&
                    noteEndTick > cell.getFirst()) {
                int clickedTick = cell.getFirst();
                if (noteStartTick == clickedTick) {
                    note.setDuration(note.getDuration() - 1);
                    note.setEntryTick(note.getEntryTick() + 1);
                } else if (noteEndTick - 1 == clickedTick) {
                    note.setDuration(note.getDuration() - 1);
                } else {
                    Note newNote = new Note(midiNote, note.getVelocity(), noteEndTick - clickedTick - 1, clickedTick + 1);
                    controller.addNote(newNote);
                    note.setDuration(clickedTick - noteStartTick);
                }

                if (note.getDuration() <= 0) {
                    noteToRemove = note;
                }

                break;
            }
        }

        if (noteToRemove != null) {
            controller.removeNote(noteToRemove);
        }
    }

    private Tuple<Integer, Integer> fromAbsToGridPos(int x, int y) {
        return new Tuple<>(x / gridCellWidth, y / gridCellHeight);
    }

    private void addCellsToPaint(Tuple<Integer, Integer> start, Tuple<Integer, Integer> end) {
        int startX = Math.max(0, Math.min(start.getFirst(), end.getFirst()));
        int endX = Math.min(controller.getTicks() - 1, Math.max(start.getFirst(), end.getFirst()));
        int startY = start.getSecond();

        for (int x = startX; x <= endX; x++) {
            boolean isStart = (x == startX);
            boolean isEnd = (x == endX);
            cellsToPaint.add(new CellToPaint(x, startY, endX - startX + 1, isStart, isEnd));
        }

        // Add note to the controller
        Note note = new Note(topNote - startY, 100, Math.max(1, endX - startX + 1), Math.min(startX, endX));
        controller.addNote(note);
    }

    public SequencerModalNotesController getController() {
        return controller;
    }

    public void setController(SequencerModalNotesController controller) {
        this.controller = controller;
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

        // Paint hover effect
        if (hoveredCell != null) {
            int x = hoveredCell.getFirst();
            int y = hoveredCell.getSecond();

            if (x >= 0 && x < controller.getTicks() && y >= 0 && (topNote - y) >= 0) {
                g.setColor(new Color(173, 216, 230, 128)); // Light blue with transparency
                g.fillRect(x * gridCellWidth, y * gridCellHeight, gridCellWidth, gridCellHeight);

                // Draw note name
                int midiNote = topNote - y;
                String noteName = Note.getNoteName(midiNote);
                g.setColor(Color.WHITE);
                g.drawString(noteName, x * gridCellWidth + 5, y * gridCellHeight + 15);
            }
        }

        // Drag preview effect
        if (dragStart != null && dragEnd != null) {
            int startX = Math.max(0, Math.min(dragStart.getFirst(), dragEnd.getFirst()));
            int endX = Math.min(controller.getTicks() - 1, Math.max(dragStart.getFirst(), dragEnd.getFirst()));
            int startY = dragStart.getSecond();

            g.setColor(new Color(173, 216, 230, 128)); // Light blue with transparency

            for (int x = startX; x <= endX; x++) {
                for (int y = startY; y <= startY; y++) {
                    g.fillRect(x * gridCellWidth, y * gridCellHeight, gridCellWidth, gridCellHeight);
                }
            }
        }
    }

    public int getGridCellWidth() {
        return gridCellWidth;
    }

    public List<Note> getNotes() {
        return controller.getNotes();
    }

    private class CellToPaint {
        int x, y, length; // Length represents the duration of the note
        boolean isStart, isEnd;

        public CellToPaint(int x, int y, int length, boolean isStart, boolean isEnd) {
            this.x = x;
            this.y = y;
            this.length = length;
            this.isStart = isStart;
            this.isEnd = isEnd;
        }

        public void paint(Graphics g) {
            g.setColor(Color.BLUE); // Finalized cells are painted blue
            int arcSize = 10;

            if (isStart && isEnd) {
                // Fully rounded corners for single-cell notes
                g.fillRoundRect(x * gridCellWidth, y * gridCellHeight, gridCellWidth, gridCellHeight, arcSize, arcSize);
            } else if (isStart) {
                // Rounded left corner
                g.fillRoundRect(x * gridCellWidth, y * gridCellHeight, gridCellWidth, gridCellHeight, arcSize, arcSize);
                g.fillRect((x + 1) * gridCellWidth - arcSize / 2, y * gridCellHeight, gridCellWidth - arcSize, gridCellHeight);
            } else if (isEnd) {
                // Rounded right corner
                g.fillRoundRect(x * gridCellWidth, y * gridCellHeight, gridCellWidth, gridCellHeight, arcSize, arcSize);
                g.fillRect(x * gridCellWidth, y * gridCellHeight, gridCellWidth - arcSize / 2, gridCellHeight);
            } else {
                // Regular rectangle for middle cells
                g.fillRect(x * gridCellWidth, y * gridCellHeight, gridCellWidth, gridCellHeight);
            }

            // Write the note name in the center of the note
            if (length > 0 && isStart && length == 1 || isStart && length > 1) {
                int midiNote = topNote - y;
                String noteName = Note.getNoteName(midiNote);
                g.setColor(Color.WHITE);
                g.drawString(noteName, x * gridCellWidth + gridCellWidth / 4, y * gridCellHeight + gridCellHeight / 2);
            }
        }
    }
}
