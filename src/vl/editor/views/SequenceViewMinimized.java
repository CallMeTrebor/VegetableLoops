package vl.editor.views;

import vl.common.VLConstants;
import vl.editor.controllers.SequenceController;
import vl.editor.models.Note;

import javax.swing.*;
import java.awt.*;

public class SequenceViewMinimized extends JPanel {
    private Color backgroundColor;
    private Color noteColor;
    private SequenceController sequenceController;

    public SequenceViewMinimized() {
        this(Color.BLUE, Color.RED);
    }

    public SequenceViewMinimized(Color backgroundColor, Color noteColor) {
        this.backgroundColor = backgroundColor;
        this.noteColor = noteColor;
        setBackground(VLConstants.BACKGROUND_COLOR);

        // on right click show a popup menu
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteOption = new JMenuItem("Delete");
        deleteOption.addActionListener(e -> {
            if (sequenceController != null) {
                sequenceController.deleteSequence();
            }
        });

        JMenuItem editOption = new JMenuItem("Edit");
        editOption.addActionListener(e -> {
            if (sequenceController != null) {
                sequenceController.editSequence();
            }
        });

        JMenuItem copyAfterOption = new JMenuItem("Copy After");
        copyAfterOption.addActionListener(e -> {
            if (sequenceController != null) {
                sequenceController.copySequenceAfter();
            }
        });

        JMenuItem copyBeforeOption = new JMenuItem("Copy Before");
        copyBeforeOption.addActionListener(e -> {
            if (sequenceController != null) {
                sequenceController.copySequenceBefore();
            }
        });

        popupMenu.add(editOption);
        popupMenu.add(copyBeforeOption);
        popupMenu.add(copyAfterOption);
        popupMenu.add(deleteOption);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    public void setSequenceController(SequenceController sequenceController) {
        this.sequenceController = sequenceController;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }

    public Color getNoteColor() {
        return noteColor;
    }

    public void setNoteColor(Color noteColor) {
        this.noteColor = noteColor;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fill the background
        g.setColor(backgroundColor);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        int panelHeight = getHeight();
        int panelWidth = getWidth();

        // Compute block dimensions
        int blockHeight = panelHeight / 32;
        int totalTicks = sequenceController.getTicks();

        // Calculate exact pixel positions for each tick
        int[] tickPositions = new int[totalTicks + 1];
        for (int i = 0; i <= totalTicks; i++) {
            tickPositions[i] = (int) Math.round((double) i / totalTicks * panelWidth);
        }

        int topNote = sequenceController.getNotes().stream().mapToInt(Note::getNote).max().orElse(0);

        // Draw each note
        for (Note note : sequenceController.getNotes()) {
            g.setColor(noteColor);
            int entryTick = (int) Math.min(note.getEntryTick(), totalTicks); // Clamp entry tick
            int endTick = (int) Math.min(note.getEntryTick() + note.getDuration(), totalTicks); // Clamp end tick

            // Skip invalid notes
            if (entryTick >= totalTicks || endTick <= entryTick) {
                continue;
            }


            int x = tickPositions[entryTick];
            int width = tickPositions[endTick] - x;
            int y = (topNote - note.getNote()) * blockHeight;

            // Ensure note width and position fit within panel bounds
            width = Math.min(width, panelWidth - x);

            g.fillRoundRect(x, y, width, blockHeight, 5, 5);

            // Write the note name
            g.setColor(VLConstants.TEXT_COLOR);
            g.drawString(Note.getNoteName(note.getNote()), x + 5, y + blockHeight / 2);
        }

        // Draw a line at the bottom of the panel
        int lineHeight = 5;
        g.fillRect(0, panelHeight - lineHeight, panelWidth, lineHeight);
    }
}