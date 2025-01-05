package vl.util;

import java.awt.*;

public class VerticalFlowLayout extends FlowLayout {
    public VerticalFlowLayout() {
        super(FlowLayout.LEFT, 0, 0); // No gaps by default
    }

    @Override
    public Dimension preferredLayoutSize(Container target) {
        synchronized (target.getTreeLock()) {
            int width = target.getWidth();
            if (width == 0) width = Integer.MAX_VALUE;
            int height = 0;
            for (Component component : target.getComponents()) {
                Dimension d = component.getPreferredSize();
                height += d.height;
            }
            Insets insets = target.getInsets();
            return new Dimension(width, height + insets.top + insets.bottom);
        }
    }

    @Override
    public void layoutContainer(Container target) {
        synchronized (target.getTreeLock()) {
            Insets insets = target.getInsets();
            int width = target.getWidth() - insets.left - insets.right;
            int y = insets.top;

            for (Component component : target.getComponents()) {
                Dimension d = component.getPreferredSize();
                component.setBounds(insets.left, y, width, d.height);
                y += d.height;
            }
        }
    }
}