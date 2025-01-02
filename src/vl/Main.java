package vl;

import vl.welcome.controllers.WelcomeScreenController;
import vl.welcome.views.WelcomeScreenWindow;

public class Main {
    public static void main(String[] args) {
        WelcomeScreenWindow welcomeScreenWindow = new WelcomeScreenWindow();
        WelcomeScreenController wc = new WelcomeScreenController(welcomeScreenWindow);
        welcomeScreenWindow.setVisible(true);
    }
}