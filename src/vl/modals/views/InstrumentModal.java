package vl.modals.views;

import vl.common.VLButton;
import vl.common.VLConstants;
import vl.common.VLLabel;
import vl.editor.models.InstrumentTypeModel;
import vl.modals.controllers.InstrumentModalController;

import javax.swing.*;
import java.awt.*;

public class InstrumentModal extends JFrame {
    private final JTextField instrumentNameField;
    private final JSlider instrumentVolumeSlider;

    private InstrumentModalController instrumentModalController;

    public InstrumentModal() {
        setTitle("Instrument Modal");
        setLayout(new GridBagLayout());
        setSize(300, 200);
        getContentPane().setBackground(VLConstants.BACKGROUND_COLOR);
        setLocationRelativeTo(null);

        instrumentNameField = new JTextField();
        VLLabel instrumentNameLabel = new VLLabel("Instrument Name");
        VLLabel instrumentVolumeLabel = new VLLabel("Instrument Volume");
        instrumentVolumeSlider = new JSlider();
        VLButton saveButton = new VLButton("Save");

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(instrumentNameLabel, c);

        c.gridx = 1;
        add(instrumentNameField, c);

        c.gridx = 0;
        c.gridy = 1;
        add(instrumentVolumeLabel, c);

        c.gridx = 1;
        add(instrumentVolumeSlider, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        add(saveButton, c);

        saveButton.addActionListener(e -> {
            if (instrumentModalController == null) return;
            instrumentModalController.onModalExit(
                    new InstrumentTypeModel(
                            0,
                            instrumentNameField.getText(),
                            instrumentVolumeSlider.getValue()
                    )
            );
        });
    }

    public void setInstrumentModalController(InstrumentModalController instrumentModalController) {
        this.instrumentModalController = instrumentModalController;
    }
}
