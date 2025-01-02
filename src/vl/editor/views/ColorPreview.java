package vl.editor.views;

import javax.swing.*;
import java.awt.*;

public class ColorPreview extends JPanel {
    private Color color;

    public ColorPreview(Color color, int width, int height) {
        this.color = color;
        setLayout(new BorderLayout());
        setBounds(0, 0, width, height);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
    }
}
