package vl.editor.views;

import vl.common.VLButton;
import vl.common.VLConstants;
import vl.editor.controllers.EditorController;
import vl.editor.controllers.InstrumentRowController;
import vl.editor.controllers.InstrumentTypeController;
import vl.editor.controllers.SequenceController;
import vl.editor.models.EditorModel;
import vl.editor.models.InstrumentRowModel;
import vl.editor.models.InstrumentTypeModel;
import vl.modals.controllers.InstrumentModalController;
import vl.modals.views.InstrumentModal;
import vl.util.VerticalFlowLayout;

import javax.swing.*;
import java.awt.*;

public class EditorWindow extends JFrame {
    private final EditorController editorController;
    JPanel instrumentTypesPanel = new JPanel();
    JPanel editorPanel = new JPanel();

    public EditorWindow() {
        setTitle("VegetableLoops");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        editorController = new EditorController(new EditorModel(), new EditorViewPanel());

        // Create editor panel with ruler and instrument rows
        editorPanel.setLayout(new BorderLayout());

        // Ruler panel at the top
        RulerPanel rulerPanel = new RulerPanel();
        rulerPanel.setPreferredSize(new Dimension(800, 50)); // Adjust height as needed
        editorPanel.add(rulerPanel, BorderLayout.NORTH);

        // Panel for instrument rows in the center
        JPanel centerPanel = new JPanel();

        // TODO: Figure out how to ditch box layout for vertical flow layout
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        editorPanel.add(centerPanel, BorderLayout.CENTER);

        // Split screen: left for instrument row, right for editor
        instrumentTypesPanel.setLayout(new VerticalFlowLayout());
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, instrumentTypesPanel, editorPanel);
        splitPane.setDividerLocation(200);
        splitPane.setResizeWeight(0.0);

        instrumentTypesPanel.setBackground(VLConstants.BACKGROUND_COLOR);
        // add a space in the panel with the height of the ruler panel
        JPanel space = new JPanel();
        space.setPreferredSize(new Dimension(instrumentTypesPanel.getWidth(), editorPanel.getPreferredSize().getSize().height));

        // Toolbar setup
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        VLButton addInstrumentButton = new VLButton("Add Instrument");
        toolBar.add(addInstrumentButton);
        VLButton addSequenceButton = new VLButton("Add Sequence");
        toolBar.add(addSequenceButton);

        addInstrumentButton.addActionListener(e -> {
            InstrumentModal instrumentModal = new InstrumentModal();
            InstrumentModalController instrumentModalController = new InstrumentModalController(instrumentModal);
            instrumentModalController.setOnModalExit(this::addInstrumentRow);
            instrumentModal.setVisible(true);
        });

        addSequenceButton.addActionListener(e -> {
            SequenceController sequenceController = new SequenceController();
            sequenceController.launchModal();
        });

        add(toolBar, BorderLayout.NORTH);
        add(splitPane);
    }

    public InstrumentTypeController addInstrumentRow(InstrumentTypeModel instrumentTypeModel) {
        InstrumentTypeController instrumentTypeController = new InstrumentTypeController(
                instrumentTypeModel,
                new InstrumentTypeView()
        );
        instrumentTypesPanel.add(instrumentTypeController.getView());
        instrumentTypesPanel.revalidate();
        instrumentTypesPanel.repaint();

        InstrumentRowView instrumentRowView = new InstrumentRowView();
        instrumentRowView.setController(new InstrumentRowController(new InstrumentRowModel(), instrumentRowView));

        // Add the new instrument row to the center panel
        JPanel centerPanel = (JPanel) editorPanel.getComponent(1); // Assumes center is the second child
        centerPanel.add(instrumentRowView);
        centerPanel.revalidate();
        centerPanel.repaint();

        return instrumentTypeController;
    }
}
