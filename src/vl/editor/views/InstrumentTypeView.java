package vl.editor.views;

import javax.swing.*;
import java.awt.*;

public class InstrumentTypeView extends JPanel {
    private final JLabel instrumentTypeNameLabel;
    private final ColorPreview colorPreview;
    private final JTextField volumeTextField;

    public InstrumentTypeView() {
        setLayout(new BorderLayout()); // Stack instrument types vertically
        setBackground(Color.DARK_GRAY); // Set background color
        setPreferredSize(new Dimension(200, 100)); // Set fixed size for each instrument type view

        instrumentTypeNameLabel = new JLabel("Instrument Type");
        instrumentTypeNameLabel.setForeground(Color.WHITE);
        instrumentTypeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(instrumentTypeNameLabel, BorderLayout.NORTH);

        colorPreview = new ColorPreview(Color.BLACK, 10, 5);
        add(colorPreview, BorderLayout.CENTER);

        volumeTextField = new JTextField("100");
        volumeTextField.setHorizontalAlignment(SwingConstants.CENTER);
        add(volumeTextField, BorderLayout.SOUTH);
    }

    public void setInstrumentTypeName(String instrumentTypeName) {
        instrumentTypeNameLabel.setText(instrumentTypeName);
    }

    public String getInstrumentTypeName() {
        return instrumentTypeNameLabel.getText();
    }

    public void setVolume(double volume) {
        volumeTextField.setText(String.valueOf(volume));
    }

    public double getVolume() {
        return Double.parseDouble(volumeTextField.getText());
    }

    public Color getColor() {
        return colorPreview.getColor();
    }

    public void setColor(Color color) {
        colorPreview.setColor(color);
    }
}