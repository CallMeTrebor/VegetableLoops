package vl.welcome.views;

import vl.common.VLButton;
import vl.common.VLLabel;
import vl.welcome.controllers.WelcomeScreenController;

import javax.swing.*;
import java.awt.*;

public class WelcomeScreenWindow extends JFrame {
    private final VLButton continueButton;
    private WelcomeScreenController controller;

    public WelcomeScreenWindow() {
        setTitle("Welcome");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                controller.onExitButtonClick();
            }
        });
        setLocationRelativeTo(null);

        // create layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;

        // create buttons
        continueButton = new VLButton("Continue last project");
        continueButton.addActionListener(e -> controller.onContinueButtonClick());
        Dimension buttonSize = continueButton.getPreferredSize();

        VLButton newProjectButton = new VLButton("New Project");
        newProjectButton.setPreferredSize(buttonSize);
        newProjectButton.addActionListener(e -> controller.onNewProjectButtonClick());

        VLButton openProjectButton = new VLButton("Open Project");
        openProjectButton.setPreferredSize(buttonSize);
        openProjectButton.addActionListener(e -> controller.onOpenProjectButtonClick());

        VLButton exitButton = new VLButton("Exit");
        exitButton.setPreferredSize(buttonSize);
        exitButton.addActionListener(e -> controller.onExitButtonClick());

        // set title props
        VLLabel appNameText = new VLLabel(
                "<html>" +
                        "<span style='color:green;'>Vegetable</span>" +
                        "<span style='color:yellow;'>Loops</span>" +
                        "</html>"
        );
        appNameText.setFont(appNameText.getFont().deriveFont(30.0f));

        // set background color
        getContentPane().setBackground(Color.DARK_GRAY);

        // add components
        add(appNameText, gbc);
        add(continueButton, gbc);
        add(newProjectButton, gbc);
        add(openProjectButton, gbc);
        add(exitButton, gbc);
    }

    private void updateWelcomeBackMode() {
        boolean shouldShowContinueButton = (controller != null && controller.userHasLastProjectFile());
        if (shouldShowContinueButton) {
            setTitle("Welcome back");
        } else {
            setTitle("Welcome");
        }

        // disable continue button if there is no last project file
        continueButton.setEnabled(shouldShowContinueButton);
    }

    public WelcomeScreenController getController() {
        return controller;
    }

    public void setController(WelcomeScreenController controller) {
        this.controller = controller;
        updateWelcomeBackMode();
    }
}