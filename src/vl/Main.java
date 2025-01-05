package vl;

import vl.common.Settings;
import vl.common.VLConstants;
import vl.editor.controllers.InstrumentRowController;
import vl.editor.controllers.InstrumentTypeController;
import vl.editor.controllers.SequenceController;
import vl.editor.models.InstrumentTypeModel;
import vl.editor.models.Note;
import vl.editor.views.EditorWindow;
import vl.welcome.controllers.WelcomeScreenController;

import java.awt.*;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Settings.getSettingsFromFile(VLConstants.SETTINGS_FILE_NAME);
        WelcomeScreenController wc = new WelcomeScreenController();
        wc.setOnWelcomeScreenClose(Main::startEditor);
        wc.getWindow().setVisible(true);

        wc.getWindow().setIconImage(Toolkit.getDefaultToolkit().getImage(VLConstants.WINDOW_ICON_PATH));
    }

    public static Void startEditor(String projectPath) {
        // read lines from project path
        EditorWindow editorWindow = new EditorWindow();

        Path path = Path.of(projectPath);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(l -> {
                if (Objects.equals(l, "")) return;
                List<String> elems = List.of(l.split(","));
                int instrumentID = (Integer.parseInt(elems.get(0)));
                String instrumentName = elems.get(1);
                int volume = (Integer.parseInt(elems.get(2)));
                InstrumentTypeModel itm = new InstrumentTypeModel(instrumentID, instrumentName, volume);
                InstrumentTypeController itc = editorWindow.addInstrumentRow(itm);
                InstrumentRowController irc = itc.getInstrumentRowController();

                int sequenceCount = 0;
                if (elems.size() > 3)
                    sequenceCount = Integer.parseInt(elems.get(3));

                int index = 4;
                for (int i = 0; i < sequenceCount; i++) {
                    int offset = Integer.parseInt(elems.get(index++));
                    int ticks = Integer.parseInt(elems.get(index++));
                    SequenceController sc = new SequenceController(ticks, irc);

                    int noteCount = Integer.parseInt(elems.get(index++));
                    for (int j = 0; j < noteCount; j++) {
                        int note = Integer.parseInt(elems.get(index++));
                        int velocity = Integer.parseInt(elems.get(index++));
                        int duration = Integer.parseInt(elems.get(index++));
                        int entryTick = Integer.parseInt(elems.get(index++));

                        sc.addNote(new Note(note, velocity, duration, entryTick));
                    }
                    irc.addSequence(sc, offset);

                    // to update the ID down the whole chain
                    itc.setInstrumentID(instrumentID);
                    itc.setVolume(volume);
                }
            });
        } catch (AccessDeniedException e) {
            System.err.println(STR."Access denied to the file: \{projectPath}");
            e.printStackTrace();
        } catch (NoSuchFileException e) {
            System.err.println(STR."File not found: \{projectPath}");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Settings.setLastOpenedProjectPath(projectPath);
        try {
            Files.writeString(Path.of(VLConstants.SETTINGS_FILE_NAME), projectPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        editorWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(VLConstants.WINDOW_ICON_PATH));
        editorWindow.setVisible(true);
        return null;
    }
}