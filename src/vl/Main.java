package vl;

import vl.editor.controllers.InstrumentRowController;
import vl.editor.controllers.SequenceController;
import vl.editor.views.EditorWindow;
import vl.editor.views.InstrumentRowView;
import vl.editor.views.SequenceViewMinimized;
import vl.modals.views.SequencerModal;
import vl.welcome.controllers.WelcomeScreenController;
import vl.welcome.views.WelcomeScreenWindow;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
//        JFrame frame = new JFrame("Hello World");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JPanel pane = new JPanel();
//
//        InstrumentRowController instrumentRowController = new InstrumentRowController();
//        InstrumentRowView instrumentRowView = instrumentRowController.getView();
//
//        // Add InstrumentRowView to the pane
//        pane.add(instrumentRowView);
//        frame.add(pane);
//        frame.setSize(800, 600);
//        frame.setVisible(true);
//
//        // Test sequence creation
//        SequenceController sequenceController = new SequenceController();
//        sequenceController.launchModal(4); // Example tick number
//
//        instrumentRowController.addSequence(sequenceController, 0);
//        instrumentRowView.add(sequenceController.getView());
//        sequenceController.getView().setBounds(0, 0, 80, 100);
//        instrumentRowView.setBackground(Color.BLUE);
//        instrumentRowView.setBounds(0, 0, 800, 100);
//        frame.repaint();

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