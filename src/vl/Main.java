package vl;

import vl.controllers.EditorController;
import vl.controllers.InstrumentRowController;
import vl.controllers.InstrumentTypeController;
import vl.controllers.SequenceController;
import vl.models.*;
import vl.util.VerticalFlowLayout;
import vl.views.EditorViewPanel;
import vl.views.InstrumentRowView;
import vl.views.InstrumentTypeView;
import vl.views.SequenceViewMinimized;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        // Initialize the JFrame
        JFrame frame = new JFrame("Editor Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        // Create the Editor components
        EditorModel editorModel = new EditorModel();
        EditorViewPanel editorViewPanel = new EditorViewPanel();
        EditorController editorController = new EditorController(editorModel, editorViewPanel);

        // Create the InstrumentTypeView (fixed on the left)
        JPanel instrumentTypeViewpanel = new JPanel();
        instrumentTypeViewpanel.setLayout(new VerticalFlowLayout()); // Vertical stacking
        instrumentTypeViewpanel.setBackground(Color.DARK_GRAY); // Set background color for the panel
        instrumentTypeViewpanel.setPreferredSize(new Dimension(200, 600)); // Set a fixed size for the panel

        // Add some InstrumentRows to the editor
        for (int i = 0; i < 5; i++) {
            // Create a SequenceModel and populate it with notes
            SequenceModel sequence = new SequenceModel();
            sequence.add(new Note(60 + i * 2, 93, 4, i * 4));  // Add variations for each row

            // Create the SequenceViewMinimized
            SequenceViewMinimized view = new SequenceViewMinimized(sequence, Color.BLACK, Color.GREEN);

            // Create the SequenceController
            SequenceController sequenceController = new SequenceController(sequence, view);

            // Create the InstrumentRow components
            InstrumentRowModel instrumentRowModel = new InstrumentRowModel();
            instrumentRowModel.addSequence(sequenceController, i * 50);

            InstrumentRowView instrumentRowView = new InstrumentRowView(instrumentRowModel);

            // Set a preferred size for the row
            instrumentRowView.setPreferredSize(new Dimension(800, 100));

            InstrumentRowController instrumentRowController = new InstrumentRowController(instrumentRowModel, instrumentRowView);

            // Add the InstrumentRow to the Editor
            editorController.addInstrumentRow(instrumentRowController);

            // Create the InstrumentTypeView and add it to the left panel
            InstrumentTypeView instrumentTypeView = getInstrumentTypeView();
            instrumentTypeViewpanel.add(instrumentTypeView); // Add view for instrument type
        }

        // Create a JScrollPane for the rows, allowing horizontal scrolling
        JScrollPane scrollPane = new JScrollPane(editorController.getEditorViewPanel(),
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Create the JSplitPane: Left side is the instrument type, right side is the scrollable rows
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, instrumentTypeViewpanel, scrollPane);
        splitPane.setDividerLocation(200); // Set the divider location (left side width)

        // Add the split pane to the frame
        frame.add(splitPane);

        // Show the JFrame
        frame.setVisible(true);
    }

    private static InstrumentTypeView getInstrumentTypeView() {
        InstrumentTypeModel instrumentTypeModel = new InstrumentTypeModel(1, "Piano", 0.5);
        InstrumentTypeView instrumentTypeView = new InstrumentTypeView();
        InstrumentTypeController instrumentTypeController = new InstrumentTypeController(instrumentTypeModel, instrumentTypeView);
        instrumentTypeView.setInstrumentTypeName("Piano");
        instrumentTypeView.setColor(Color.BLUE);
        instrumentTypeView.setPreferredSize(new Dimension(200, 100));
        instrumentTypeView.setVolume(instrumentTypeModel.getVolume());
        return instrumentTypeView;
    }
}