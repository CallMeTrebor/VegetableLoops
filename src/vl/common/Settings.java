package vl.common;

import vl.util.FileIO;

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
        if(!FileIO.doesFileExist(filePath)) {
            return;
        } else {
            try {
                String settings = FileIO.readFromFile(filePath);
                // parse settings
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveSettingsToFile(String filePath) {

    }

    public static String getCurrentProjectPath() {
        return currentProjectPath;
    }

    public static void setCurrentProjectPath(String currentProjectPath) {
        Settings.currentProjectPath = currentProjectPath;
    }
}
