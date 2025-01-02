package vl.welcome.views;

import vl.welcome.controllers.WelcomeScreenController;

import javax.swing.*;
import java.awt.*;

public class WelcomeScreenWindow extends JFrame {
    private WelcomeScreenController controller;

    private final JLabel appNameText = new JLabel(
            "<html>" +
                    "<span style='color:green;'>Vegetable</span>" +
                    "<span style='color:yellow;'>Loops</span>" +
                    "</html>"
    );

    public WelcomeScreenWindow() {
        // SUGGESTION : Check if the user has a last project file and show the continue button only then
        // SUGGESTION : Check if the user has a last project file and write welcome back instead of welcome
        setTitle("Welcome");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;

        // create buttons
        JButton continueButton = new JButton("Continue last project");
        continueButton.addActionListener(e -> controller.onContinueButtonClick());

        JButton newProjectButton = new JButton("New Project");
        newProjectButton.addActionListener(e -> controller.onNewProjectButtonClick());

        JButton openProjectButton = new JButton("Open Project");
        openProjectButton.addActionListener(e -> controller.onOpenProjectButtonClick());

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> controller.onExitButtonClick());

        // set font size
        appNameText.setFont(appNameText.getFont().deriveFont(30.0f));

        // add components
        add(appNameText, gbc);
        add(continueButton, gbc);
        add(newProjectButton, gbc);
        add(openProjectButton, gbc);
        add(exitButton, gbc);
    }

    public void setController(WelcomeScreenController controller) {
        this.controller = controller;
    }

    public WelcomeScreenController getController() {
        return controller;
    }
}