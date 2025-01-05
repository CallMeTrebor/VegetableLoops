package vl.editor.views;

import vl.common.VLConstants;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

public class ColorPreview extends JPanel {
    private Color color;
    private Function<Color, Void> colorChangeCallback;

    public ColorPreview(Color color, int width, int height) {
        this.color = color;
        setBackground(VLConstants.BACKGROUND_COLOR);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(width, height)); // Set preferred size
        setMinimumSize(new Dimension(width, height));   // Set minimum size

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Color newColor = JColorChooser.showDialog(null, "Choose a color", color);
                if (newColor != null) {
                    setColor(newColor);
                    if (colorChangeCallback != null)
                        colorChangeCallback.apply(newColor);
                }
            }
        });
    }

    public void setColorChangeCallback(Function<Color, Void> colorChangeCallback) {
        this.colorChangeCallback = colorChangeCallback;
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
