package vl.editor.views;

import vl.common.VLConstants;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NoteScalePanel extends JPanel {
    private final List<String> notes; // List of notes to display

    public NoteScalePanel(List<String> notes, int rowHeight) {
        this.notes = notes;
        setPreferredSize(new Dimension(50, notes.size() * rowHeight));
        setBackground(VLConstants.BACKGROUND_COLOR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(VLConstants.TEXT_COLOR);
        g.setFont(new Font("Arial", Font.PLAIN, 12));

        int rowHeight = getHeight() / notes.size();
        for (int i = 0; i < notes.size(); i++) {
            int y = (i + 1) * rowHeight - (rowHeight / 4);
            g.drawString(notes.get(i), 5, y); // Draw the note names with padding
        }
    }
}