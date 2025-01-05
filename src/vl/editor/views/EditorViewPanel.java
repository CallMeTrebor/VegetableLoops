package vl.editor.views;

import vl.common.VLConstants;

import javax.swing.*;

public class EditorViewPanel extends JPanel {
    public EditorViewPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(VLConstants.BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // No outer margins
    }

    public void addView(InstrumentRowView instrumentRowView) {
        add(instrumentRowView);
    }

    public void removeView(InstrumentRowView instrumentRowView) {
        remove(instrumentRowView);
    }

    public void clearView() {
        removeAll();
        revalidate();
        repaint();
    }
}
