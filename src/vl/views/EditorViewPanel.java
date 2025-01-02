package vl.views;

import vl.controllers.InstrumentRowController;
import vl.util.VerticalFlowLayout;

import javax.swing.*;

public class EditorViewPanel extends JPanel {
    public EditorViewPanel() {
        setLayout(new VerticalFlowLayout());
        setBackground(InstrumentRowView.BACKGROUND_COLOR);
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
