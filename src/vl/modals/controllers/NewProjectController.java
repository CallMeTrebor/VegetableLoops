package vl.modals.controllers;

import vl.common.VLConstants;
import vl.modals.views.NewProjectModal;

import javax.swing.*;
import java.util.function.Function;

public class NewProjectController {
    private final NewProjectModal newProjectModal;

    public NewProjectController() {
        this(new NewProjectModal());
    }

    public NewProjectController(NewProjectModal newProjectModal) {
        this.newProjectModal = newProjectModal;
        newProjectModal.setOnBrowse(this::onBrowse);
        newProjectModal.setOnCreate(this::onCreate);
    }

    private Void onBrowse(Void unused) {
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

        int result = fileChooser.showOpenDialog(newProjectModal);
        if (result == JFileChooser.APPROVE_OPTION) {
            newProjectModal.setProjectNameText(fileChooser.getSelectedFile().getName());
            newProjectModal.setProjectPathText(fileChooser.getSelectedFile().getAbsolutePath());
        }

        return null;
    }

    public Void onCreate(Void unused) {
        newProjectModal.dispose();
        return null;
    }

    public void setOnClose(Function<Boolean, Void> onClose) {
        newProjectModal.setOnClose(onClose);
    }
}
