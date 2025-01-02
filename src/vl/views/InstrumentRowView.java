package vl.views;

import vl.controllers.SequenceController;
import vl.models.InstrumentRowModel;
import vl.util.Tuple;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InstrumentRowView extends JPanel {
    private InstrumentRowModel model;
    public static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
    private int height = 100;

    public InstrumentRowView(InstrumentRowModel model) {
        this.model = model;
        setLayout(null); // Use null layout since we set bounds manually
        setBounds(0, 0, 800, height); // Set bounds manually

        updateView(); // Populate the panel with SequenceViewMinimized instances
    }

    public InstrumentRowModel getModel() {
        return model;
    }

    public void setModel(InstrumentRowModel model) {
        this.model = model;
        updateView(); // Refresh the panel when the model changes
    }

    private void updateView() {
        removeAll(); // Clear previous components

        List<Tuple<SequenceController, Integer>> sequences = model.getSequences();
        for (Tuple<SequenceController, Integer> sequence : sequences) {
            SequenceViewMinimized originalView = sequence.getFirst().getView();

            // Create a new view so we don't affect the original one
            SequenceViewMinimized view = new SequenceViewMinimized(originalView.getSequenceModel(),
                    originalView.getBackgroundColor(), originalView.getNoteColor());
            int xCoord = sequence.getSecond();
            view.setBounds(xCoord, 0, view.getPreferredSize().width, height); // Set bounds manually
            add(view); // Add the SequenceViewMinimized to the panel
        }

        setBackground(BACKGROUND_COLOR);
        revalidate(); // Refresh the layout
        repaint(); // Repaint the panel
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}