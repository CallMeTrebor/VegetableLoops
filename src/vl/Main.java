package vl;

import vl.editor.controllers.SequenceController;
import vl.editor.views.EditorWindow;
import vl.modals.views.SequencerModal;
import vl.welcome.controllers.WelcomeScreenController;
import vl.welcome.views.WelcomeScreenWindow;

public class Main {
    public static void main(String[] args) {
        WelcomeScreenWindow welcomeScreenWindow = new WelcomeScreenWindow();
        WelcomeScreenController wc = new WelcomeScreenController(welcomeScreenWindow);
        wc.setOnWelcomeScreenClose(Main::startEditor);
        welcomeScreenWindow.setVisible(true);
    }

    public static Void startEditor(String projectPath) {
        EditorWindow editorWindow = new EditorWindow();
        editorWindow.setVisible(true);

        return null;
    }
}