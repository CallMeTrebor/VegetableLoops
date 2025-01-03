package vl.editor.views;

import vl.common.VLConstants;

import javax.swing.*;
import java.awt.*;

public class RulerPanel extends JPanel {
    private int tickSpacing; // Spacing between ticks (in "ticks")
    private int pixelsPerTick; // Number of pixels per tick
    private int totalTicks; // Total ticks to display on the ruler

    public RulerPanel(int tickSpacing, int pixelsPerTick, int totalTicks) {
        this.tickSpacing = tickSpacing;
        this.pixelsPerTick = pixelsPerTick;
        this.totalTicks = totalTicks;
        setPreferredSize(new Dimension(totalTicks * pixelsPerTick, 10));
        setBackground(VLConstants.BACKGROUND_COLOR);
    }

    public RulerPanel() {
        this(10, 20, 100);
    }

    public void setPixelsPerTick(int pixelsPerTick) {
        this.pixelsPerTick = pixelsPerTick;
        repaint();
    }

    public void setTotalTicks(int totalTicks) {
        this.totalTicks = totalTicks;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(VLConstants.TEXT_COLOR);
        int height = getHeight();

        for (int i = 0; i <= totalTicks; i++) {
            int x = i * pixelsPerTick;

            // Draw tick marks
            if (i % tickSpacing == 0) {
                g.drawLine(x, 0, x, height / 2); // Long tick
            } else {
                g.drawLine(x, 0, x, height / 4); // Short tick
            }
        }
    }

    public int getTickSpacing() {
        return tickSpacing;
    }

    public void setTickSpacing(int tickSpacing) {
        this.tickSpacing = tickSpacing;
    }

    public int getPixelsPerTick() {
        return pixelsPerTick;
    }

    public int getTotalTicks() {
        return totalTicks;
    }
}