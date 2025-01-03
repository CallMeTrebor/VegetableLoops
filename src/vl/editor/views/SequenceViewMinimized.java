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

        setPreferredSize(new Dimension(100, 50)); // Set a fixed size for the panel
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

    // TODO: REDO METHOD
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (sequenceModel == null) {
            return; // Avoid rendering if no model is set
        }

        // Fill the background
        setBackground(VLConstants.BACKGROUND_COLOR);
        g.setColor(backgroundColor);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        // Sort notes by pitch
        sequenceModel.getNotes().sort((a, b) -> Integer.compare(a.getNote(), b.getNote()));

        // Determine bounds for note rendering
        int totalDuration = sequenceModel.getNotes().stream().mapToInt(Note::getDuration).sum();
        int noteCount = sequenceModel.getNotes().size();
        int panelHeight = getHeight();
        int panelWidth = getWidth();

        // Compute height for each note block and initialize x position
        int blockHeight = panelHeight / noteCount;
        int x = 0;

        // Set note color and draw rectangles
        g.setColor(noteColor);
        for (Note note : sequenceModel.getNotes()) {
            int width = note.getDuration() * panelWidth / totalDuration; // Scale width by note duration
            int y = panelHeight - blockHeight * (noteCount--); // Map note to its vertical order

            g.fillRect(x, y, width, blockHeight);
            x += width; // Increment x position
        }

        // draw a line at the bottom of the panel the height of 10 pixels
        int lineHeight = 5;
        g.fillRect(0, panelHeight - lineHeight, panelWidth, lineHeight);
    }
}