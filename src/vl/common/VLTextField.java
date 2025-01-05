package vl.common;

import javax.swing.*;

public class VLTextField extends JTextField {
    public VLTextField() {
        this("");
    }

    public VLTextField(String content) {
        super(content);
        setBackground(VLConstants.BACKGROUND_COLOR);
        setForeground(VLConstants.TEXT_COLOR);
    }
}
