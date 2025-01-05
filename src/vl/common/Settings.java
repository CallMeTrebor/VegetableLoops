package vl.common;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class Settings {
    private static String lastOpenedProjectPath = "";
    private static String currentProjectPath = "";

    public static String getLastOpenedProjectPath() {
        return lastOpenedProjectPath;
    }

    public static void setLastOpenedProjectPath(String lastOpenedProjectPath) {
        Settings.lastOpenedProjectPath = lastOpenedProjectPath;
    }

    // TODO: fill in when the settings file format is decided
    public static void getSettingsFromFile(String filePath) {
        Path path = Path.of(filePath);
        if (Files.exists(path)) {
            try (var lines = Files.lines(path)) {
                lastOpenedProjectPath = lines.collect(Collectors.joining("\n"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getCurrentProjectPath() {
        return currentProjectPath;
    }

    public static void setCurrentProjectPath(String currentProjectPath) {
        Settings.currentProjectPath = currentProjectPath;
    }
}
