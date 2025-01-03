package vl.welcome.controllers;

import vl.common.Settings;
import vl.common.VLConstants;
import vl.modals.controllers.NewProjectController;
import vl.modals.views.NewProjectModal;
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

    public WelcomeScreenController(WelcomeScreenWindow welcomeScreenWindow) {
        this.welcomeScreenWindow = welcomeScreenWindow;
        welcomeScreenWindow.setController(this);
    }

    public void onContinueButtonClick() {
        if(Objects.equals(Settings.getLastOpenedProjectPath(), ""))
            Settings.getSettingsFromFile(VLConstants.SETTINGS_FILE_NAME);

        // load the last project file
        Settings.setCurrentProjectPath(Settings.getLastOpenedProjectPath());
    }

    public void onNewProjectButtonClick() {
        // prompt the user to create a new project file somewhere
        Function<Boolean, Void> modalCallback = (Boolean success) -> {
            if (success) {
                // load the newly created project file
                welcomeScreenWindow.dispose();
            }
            return null;
        };

        NewProjectModal newProjectModal = new NewProjectModal();
        NewProjectController newProjectController = new NewProjectController(newProjectModal);
        newProjectModal.setVisible(true);
        newProjectController.setOnClose(e -> {
            modalCallback.apply(e);
            onWelcomeScreenClose.apply(Settings.getCurrentProjectPath());
            return null;
        });
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
            welcomeScreenWindow.dispose();
            onWelcomeScreenClose.apply(Settings.getCurrentProjectPath());
        }
    }

    public void onExitButtonClick() {
        welcomeScreenWindow.dispose();
    }

    public boolean userHasLastProjectFile() {
        if(Objects.equals(Settings.getLastOpenedProjectPath(), ""))
            Settings.getSettingsFromFile(VLConstants.SETTINGS_FILE_NAME);

        return !Objects.equals(Settings.getLastOpenedProjectPath(), "");
    }

    public void setOnWelcomeScreenClose(Function<String, Void> onWelcomeScreenClose) {
        this.onWelcomeScreenClose = onWelcomeScreenClose;
    }
}
