package vl.common;

import javax.swing.*;

public class VLButton extends JButton {
    public VLButton() {
        this("");
    }

    public VLButton(String text) {
        super(text);
        setBackground(VLConstants.BUTTON_COLOR);
        setForeground(VLConstants.TEXT_COLOR);
    }
}
