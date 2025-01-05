package vl.editor.views;

import vl.common.VLButton;
import vl.common.VLConstants;
import vl.common.VLLabel;
import vl.editor.controllers.EditorController;
import vl.editor.controllers.InstrumentRowController;
import vl.editor.controllers.InstrumentTypeController;
import vl.editor.models.EditorModel;
import vl.editor.models.InstrumentRowModel;
import vl.editor.models.InstrumentTypeModel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class EditorWindow extends JFrame {
    private final EditorController editorController;
    JPanel instrumentTypesPanel = new JPanel();
    JPanel editorPanel = new JPanel();
    VLButton playButton = new VLButton("Play");

    java.util.List<InstrumentTypeController> instrumentTypeControllers = new LinkedList<>();

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
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        editorPanel.add(centerPanel, BorderLayout.CENTER);

        // Split screen: left for instrument row, right for editor
        instrumentTypesPanel.setLayout(new BoxLayout(instrumentTypesPanel, BoxLayout.Y_AXIS));

        VLLabel instrumentsLabel = new VLLabel("Instruments");
        instrumentsLabel.setPreferredSize(new Dimension(200, 50));
        instrumentsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        instrumentsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        instrumentTypesPanel.add(instrumentsLabel);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, instrumentTypesPanel, editorPanel);
        splitPane.setDividerLocation(200);
        splitPane.setResizeWeight(0.0);

        instrumentTypesPanel.setBackground(VLConstants.BACKGROUND_COLOR);
        // add a space in the panel with the height of the ruler panel
        JPanel space = new JPanel();
        space.setPreferredSize(new Dimension(instrumentTypesPanel.getWidth(), editorPanel.getPreferredSize().getSize().height));

        // Toolbar setup
        JToolBar toolBar = new JToolBar();
        toolBar.setBackground(VLConstants.BACKGROUND_COLOR);
        toolBar.setFloatable(false);

        VLButton saveButton = new VLButton("Save");
        toolBar.add(saveButton);

        VLButton stopButton = new VLButton("Stop");
        toolBar.add(stopButton);

        saveButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save into");
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String fileToSave = fileChooser.getSelectedFile().getAbsolutePath();
                editorController.saveToFile(fileToSave, instrumentTypeControllers);
            }
        });

        toolBar.add(playButton);
        playButton.addActionListener(e -> {
            editorController.playMusic();
        });

        toolBar.add(stopButton);
        stopButton.addActionListener(e -> {
            editorController.stopMusic();
        });

        // on resize redraw all
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                super.componentResized(e);
                repaint();
            }
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

        instrumentTypeControllers.add(instrumentTypeController);

        InstrumentRowView instrumentRowView = new InstrumentRowView();
        instrumentRowView.setController(new InstrumentRowController(new InstrumentRowModel(), instrumentRowView));

        instrumentTypeController.setInstrumentRowController(instrumentRowView.getController());

        // Add the new instrument row to the center panel
        JPanel centerPanel = (JPanel) editorPanel.getComponent(1); // Assumes center is the second child
        centerPanel.add(instrumentRowView);
        centerPanel.revalidate();
        centerPanel.repaint();

        editorController.addInstrumentRowToModel(instrumentRowView.getController());
        return instrumentTypeController;
    }
}
