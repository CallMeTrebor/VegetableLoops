package vl.editor.views;

import vl.common.VLConstants;
import vl.editor.models.SequenceModel;
import vl.editor.models.Note;

import javax.swing.*;
import java.awt.*;

public class SequenceViewMinimized extends JPanel {
    private Color backgroundColor;
    private Color noteColor;
    private SequenceModel sequenceModel; // Reference to the model used only to access data, does not violate MVC

    public SequenceViewMinimized(SequenceModel sequenceModel) {
        this(sequenceModel, Color.BLUE, Color.RED);
    }

    public SequenceViewMinimized(SequenceModel sequenceModel, Color backgroundColor, Color noteColor) {
        this.sequenceModel = sequenceModel;
        this.backgroundColor = backgroundColor;
        this.noteColor = noteColor;
    }

    public SequenceModel getSequenceModel() {
        return sequenceModel;
    }

    public void setSequenceModel(SequenceModel sequenceModel) {
        this.sequenceModel = sequenceModel;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getNoteColor() {
        return noteColor;
    }

    public void setNoteColor(Color noteColor) {
        this.noteColor = noteColor;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (sequenceModel == null) {
            System.out.println("[WARN] No model set for SequenceViewMinimized: " + this);
            return; // Avoid rendering if no model is set
        }

        // Fill the background
        g.setColor(backgroundColor);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        int panelHeight = getHeight();
        int panelWidth = getWidth();

        // Compute block dimensions
        int blockHeight = panelHeight / 32;
        int totalTicks = sequenceModel.getTicks();

        // Calculate exact pixel positions for each tick
        int[] tickPositions = new int[totalTicks + 1];
        for (int i = 0; i <= totalTicks; i++) {
            tickPositions[i] = (int) Math.round((double) i / totalTicks * panelWidth);
        }

        // Draw each note
        g.setColor(noteColor);
        for (Note note : sequenceModel.getNotes()) {
            int entryTick = (int) Math.min(note.getEntryTick(), totalTicks); // Clamp entry tick
            int endTick = (int) Math.min(note.getEntryTick() + note.getDuration(), totalTicks); // Clamp end tick

            // Skip invalid notes
            if (entryTick >= totalTicks || endTick <= entryTick) {
                continue;
            }

            int x = tickPositions[entryTick];
            int width = tickPositions[endTick] - x;
            int y = note.getNote() * blockHeight;

            // Ensure note width and position fit within panel bounds
            width = Math.min(width, panelWidth - x);

            g.fillRect(x, y, width, blockHeight);
        }

        // Draw a line at the bottom of the panel
        int lineHeight = 5;
        g.fillRect(0, panelHeight - lineHeight, panelWidth, lineHeight);
    }
}