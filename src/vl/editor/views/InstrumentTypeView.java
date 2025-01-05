package vl.editor.views;

import vl.common.VLConstants;
import vl.common.VLDropDown;
import vl.common.VLLabel;
import vl.editor.controllers.InstrumentTypeController;

import javax.swing.*;
import java.awt.*;

public class InstrumentTypeView extends JPanel {
    private final ColorPreview noteColorPreview;
    private final ColorPreview backgroundColorPreview;
    private final VLDropDown instrumentDropDown;
    JSlider volumeSlider = new JSlider(JSlider.VERTICAL, 0, 100, 100);
    private InstrumentTypeController controller;

    public InstrumentTypeView() {
        setBackground(VLConstants.BACKGROUND_COLOR);
        setPreferredSize(new Dimension(200, 100));
        setLayout(new GridBagLayout());

        // GridBagConstraints instance for layout
        GridBagConstraints gbc;

        // Volume slider
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weighty = 1.0; // Allow vertical expansion
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment

        volumeSlider.setBackground(VLConstants.BACKGROUND_COLOR);
        volumeSlider.setPaintTicks(false);
        add(volumeSlider, gbc);

        volumeSlider.addChangeListener(e -> {
            setVolume(volumeSlider.getValue());
        });

        VLLabel noteColorLabel = new VLLabel("Note Color:");
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(noteColorLabel, gbc);

        // Note color preview
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 2; // Correct column placement
        gbc.gridy = 0; // Row for note preview
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE; // No resizing
        gbc.anchor = GridBagConstraints.CENTER;

        noteColorPreview = new ColorPreview(Color.RED, 25, 25);
        add(noteColorPreview, gbc);
        noteColorPreview.setColorChangeCallback(color -> {
            if (controller != null) {
                controller.setNoteColor(color);
            }
            return null;
        });

        VLLabel backgroundColorLabel = new VLLabel("Background Color:");
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(backgroundColorLabel, gbc);

        // Background color preview
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 2; // Correct column placement
        gbc.gridy = 1; // Row for background preview
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE; // No resizing
        gbc.anchor = GridBagConstraints.CENTER;

        backgroundColorPreview = new ColorPreview(Color.BLUE, 25, 25);
        add(backgroundColorPreview, gbc);
        backgroundColorPreview.setColorChangeCallback(color -> {
            if (controller != null) {
                controller.setBackgroundColor(color);
            }
            return null;
        });

        // Instrument text field
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expand horizontally
        gbc.anchor = GridBagConstraints.CENTER;

        instrumentDropDown = new VLDropDown(VLConstants.INSTRUMENT_TO_ID.keySet().stream().map(Object::toString).sorted().toArray(String[]::new));
        add(instrumentDropDown, gbc);


        instrumentDropDown.addActionListener(e -> {
            if (controller != null) {
                if (VLConstants.INSTRUMENT_TO_ID.containsKey(instrumentDropDown.getSelectedItem())) {
                    controller.setInstrumentID(VLConstants.INSTRUMENT_TO_ID.get(instrumentDropDown.getSelectedItem()));
                    controller.setInstrumentTypeName((String) instrumentDropDown.getSelectedItem());
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(VLConstants.TEXT_COLOR);
        g.drawLine(0, 0, getWidth(), 0);
    }

    public void setController(InstrumentTypeController controller) {
        this.controller = controller;

        instrumentDropDown.setSelectedItem(controller.getInstrumentTypeName());
        backgroundColorPreview.setColor(controller.getBackgroundColor());
        noteColorPreview.setColor(controller.getNoteColor());
    }

    public Color getNoteColor() {
        return noteColorPreview.getColor();
    }

    public void setNoteColor(Color color) {
        noteColorPreview.setColor(color);
    }

    public Color getBackgroundColor() {
        return backgroundColorPreview.getColor();
    }

    public void setBackgroundColor(Color color) {
        backgroundColorPreview.setColor(color);
    }

    public void setVolume(int v) {
        volumeSlider.setValue(v);

        if (controller != null && controller.getVolume() != v) {
            controller.setVolume(volumeSlider.getValue());
        }
    }
}