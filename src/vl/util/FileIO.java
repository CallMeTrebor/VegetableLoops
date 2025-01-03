package vl.util;

import vl.common.Settings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

// TODO: Implement the IO class
public class FileIO {
    public static boolean doesFileExist(String path) {
        return Files.exists(Path.of(path));
    }

    public static void writeToFile(String path, String content) throws IOException {
        Files.writeString(Path.of(path), content);
    }

    public static String readFromFile(String path) throws IOException {
        // using try to ensure resources are closed in case of an exception
        try (var lines = Files.lines(Path.of(path))) {
            return lines.collect(Collectors.joining("\n"));
        }
    }
}
