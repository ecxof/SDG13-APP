package com.example.sdg13ver5;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Best-quiz-score per topic, persisted at {@code ~/.sdg13/progress.properties}.
 * Kept as a simple key=value file so no JSON parsing is needed here.
 */
public final class ProgressStore {

    private static final Path FILE = Paths.get(
            System.getProperty("user.home"), ".sdg13", "progress.properties");

    private ProgressStore() {}

    public static Map<Integer, Integer> readAll() {
        Map<Integer, Integer> out = new HashMap<>();
        if (!Files.isRegularFile(FILE)) return out;
        try {
            for (String line : Files.readAllLines(FILE, StandardCharsets.UTF_8)) {
                if (line.isBlank() || line.startsWith("#")) continue;
                int eq = line.indexOf('=');
                if (eq <= 0) continue;
                try {
                    int id = Integer.parseInt(line.substring(0, eq).trim());
                    int score = Integer.parseInt(line.substring(eq + 1).trim());
                    out.put(id, score);
                } catch (NumberFormatException ignored) {}
            }
        } catch (IOException ignored) {}
        return out;
    }

    /** Stored value is the highest score seen so far for that topic. */
    public static int bestScore(int topicId) {
        return readAll().getOrDefault(topicId, -1);
    }

    public static void recordScore(int topicId, int score) throws IOException {
        Map<Integer, Integer> all = readAll();
        int prev = all.getOrDefault(topicId, -1);
        if (score > prev) all.put(topicId, score);
        Files.createDirectories(FILE.getParent());
        StringBuilder sb = new StringBuilder("# SDG-13 quiz best scores (topicId=score)\n");
        all.forEach((k, v) -> sb.append(k).append('=').append(v).append('\n'));
        Files.writeString(FILE, sb.toString(), StandardCharsets.UTF_8);
    }
}
