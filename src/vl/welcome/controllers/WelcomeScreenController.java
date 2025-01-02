package vl.welcome.controllers;

import vl.welcome.views.WelcomeScreenWindow;

public class WelcomeScreenController {
    private final WelcomeScreenWindow welcomeScreenWindow;

    public WelcomeScreenController(WelcomeScreenWindow welcomeScreenWindow) {
        this.welcomeScreenWindow = welcomeScreenWindow;
        welcomeScreenWindow.setController(this);
    }

    public void onContinueButtonClick() {
        // get the last project file and open it

        // load the project file
    }

    public void onNewProjectButtonClick() {
        // prompt the user to create a new project file somewhere

        // ask the user for the project setting

        // load the newly created project file
    }

    public void onOpenProjectButtonClick() {
        // prompt the user to open a project file

        // load the project file
    }

    public void onExitButtonClick() {
        welcomeScreenWindow.dispose();
    }
}
