package com.example.sdg13ver5;

import com.example.sdg13ver5.model.Topic;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Reads topic body text from the classpath (bundled defaults), with a
 * user-writable overlay at {@code ~/.sdg13/data/} for admin edits. Writes
 * always go to the overlay so a packaged/read-only install still works.
 */
public final class DataStore {

    private static final String RESOURCE_PREFIX = "/com/example/sdg13ver5/data/";
    private static final Path OVERLAY_DIR = Paths.get(
            System.getProperty("user.home"), ".sdg13", "data");

    private DataStore() {}

    public static String read(Topic topic) {
        Path overlay = OVERLAY_DIR.resolve(topic.dataFileName());
        try {
            if (Files.isRegularFile(overlay)) {
                String s = Files.readString(overlay, StandardCharsets.UTF_8).trim();
                if (!s.isEmpty()) return s;
            }
        } catch (IOException ignored) {}

        try (InputStream in = DataStore.class.getResourceAsStream(
                RESOURCE_PREFIX + topic.dataFileName())) {
            if (in != null) {
                String s = new String(in.readAllBytes(), StandardCharsets.UTF_8).trim();
                if (!s.isEmpty()) return s;
            }
        } catch (IOException ignored) {}

        return topic.defaultBody();
    }

    public static void write(Topic topic, String text) throws IOException {
        Files.createDirectories(OVERLAY_DIR);
        Files.writeString(OVERLAY_DIR.resolve(topic.dataFileName()),
                text == null ? "" : text, StandardCharsets.UTF_8);
    }
}
