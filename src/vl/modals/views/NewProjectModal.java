package vl.modals.views;

import vl.common.VLButton;
import vl.common.VLLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Function;

public class NewProjectModal extends JFrame {
    private final JTextField projectPathField;
    private final VLLabel projectNameLabel;

    // passed true to the function if the project was created successfully, false else
    private Function<Boolean, Void> onClose;
    private Function<Void, Void> onBrowse;
    private Function<Void, Void> onCreate;

    public NewProjectModal() {
        setTitle("New Project");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        VLLabel projectNameLabel = new VLLabel("Project Name");
        add(projectNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        this.projectNameLabel = new VLLabel("-");
        add(this.projectNameLabel, gbc);

        projectPathField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(projectPathField, gbc);

        VLButton browseButton = new VLButton("Browse");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(browseButton, gbc);

        VLButton createButton = new VLButton("Create");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(createButton, gbc);

        browseButton.addActionListener(e -> onBrowse.apply(null));
        createButton.addActionListener(e -> {
                if(onCreate != null) onCreate.apply(null);
                if(onClose != null) onClose.apply(true);
            }
        );

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onClose.apply(false);
            }
        });
    }

    public void setProjectNameText(String text) {
        projectNameLabel.setText(text);
    }

    public String getProjectNameText() {
        return projectNameLabel.getText();
    }

    public void setProjectPathText(String text) {
        projectPathField.setText(text);
    }

    public String getProjectPathText() {
        return projectPathField.getText();
    }

    public Function<Void, Void> getOnBrowse() {
        return onBrowse;
    }

    public void setOnBrowse(Function<Void, Void> onBrowse) {
        this.onBrowse = onBrowse;
    }

    public Function<Void, Void> getOnCreate() {
        return onCreate;
    }

    public void setOnCreate(Function<Void, Void> onCreate) {
        this.onCreate = onCreate;
    }

    public Function<Boolean, Void> getOnClose() {
        return onClose;
    }

    public void setOnClose(Function<Boolean, Void> onClose) {
        this.onClose = onClose;
    }
}