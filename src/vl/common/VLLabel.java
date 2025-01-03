package vl.common;

import javax.swing.*;

public class VLLabel extends JLabel {
    public VLLabel(String text) {
        super(text);
        setForeground(VLConstants.TEXT_COLOR);
    }
}
