package vl.welcome.controllers;

import vl.common.Settings;
import vl.common.VLConstants;
import vl.welcome.views.WelcomeScreenWindow;

import javax.swing.*;
import java.util.Objects;
import java.util.function.Function;

/**
 * The purpose of this controller, and it's window, is to set the Settings'
 * CurrentProjectPath to the file we want to edit
 */
public class WelcomeScreenController {
    private final WelcomeScreenWindow welcomeScreenWindow;

    private Function<String, Void> onWelcomeScreenClose;

    public WelcomeScreenController() {
        this(new WelcomeScreenWindow());
    }

    public WelcomeScreenController(WelcomeScreenWindow welcomeScreenWindow) {
        this.welcomeScreenWindow = welcomeScreenWindow;
        welcomeScreenWindow.setController(this);
    }

    public WelcomeScreenWindow getWindow() {
        return welcomeScreenWindow;
    }

    public void onContinueButtonClick() {
        if (Objects.equals(Settings.getLastOpenedProjectPath(), ""))
            Settings.getSettingsFromFile(VLConstants.SETTINGS_FILE_NAME);

        // load the last project file
        Settings.setCurrentProjectPath(Settings.getLastOpenedProjectPath());

        welcomeScreenWindow.dispose();
        onWelcomeScreenClose.apply(Settings.getCurrentProjectPath());
    }

    public void onNewProjectButtonClick() {
        welcomeScreenWindow.dispose();
        Settings.setCurrentProjectPath(VLConstants.DEFAULT_PROJECT_PATH);
        onWelcomeScreenClose.apply(Settings.getCurrentProjectPath());
    }

    public void onOpenProjectButtonClick() {
        // prompt the user to open a project file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(java.io.File f) {
                return f.getName().toLowerCase().endsWith(VLConstants.PROJECT_FILE_EXTENSION)
                        || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "VegetableLoops Project Files (*" + VLConstants.PROJECT_FILE_EXTENSION + ")";
            }
        });

        int result = fileChooser.showOpenDialog(welcomeScreenWindow);
        if (result == JFileChooser.APPROVE_OPTION) {
            Settings.setCurrentProjectPath(fileChooser.getSelectedFile().getAbsolutePath());
            welcomeScreenWindow.dispose();
            onWelcomeScreenClose.apply(Settings.getCurrentProjectPath());
        }
    }

    public void onExitButtonClick() {
        welcomeScreenWindow.dispose();
    }

    public boolean userHasLastProjectFile() {
        if (Objects.equals(Settings.getLastOpenedProjectPath(), ""))
            Settings.getSettingsFromFile(VLConstants.SETTINGS_FILE_NAME);

        return !Objects.equals(Settings.getLastOpenedProjectPath(), "");
    }

    public void setOnWelcomeScreenClose(Function<String, Void> onWelcomeScreenClose) {
        this.onWelcomeScreenClose = onWelcomeScreenClose;
    }
}
