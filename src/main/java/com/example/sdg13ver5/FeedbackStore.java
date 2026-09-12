package com.example.sdg13ver5;

import com.example.sdg13ver5.model.Feedback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Append-only feedback log. Uses JSON Lines (one JSON object per line) so
 * appends are O(1) and no third-party dependency is needed. Lives alongside
 * the topic-body overlay in {@code ~/.sdg13/}.
 */
public final class FeedbackStore {

    private static final Path FILE = Paths.get(
            System.getProperty("user.home"), ".sdg13", "feedback.jsonl");

    private FeedbackStore() {}

    public static Path path() { return FILE; }

    public static void append(Feedback f) throws IOException {
        Files.createDirectories(FILE.getParent());
        String line = "{\"submittedAt\":\"" + f.submittedAt().toString()
                + "\",\"name\":" + quote(f.name())
                + ",\"suggestion\":" + quote(f.suggestion()) + "}\n";
        Files.writeString(FILE, line, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public static List<Feedback> readAll() {
        List<Feedback> out = new ArrayList<>();
        if (!Files.isRegularFile(FILE)) return out;
        try {
            for (String line : Files.readAllLines(FILE, StandardCharsets.UTF_8)) {
                Feedback f = parse(line);
                if (f != null) out.add(f);
            }
        } catch (IOException ignored) {}
        return out;
    }

    private static Feedback parse(String line) {
        if (line == null || line.isBlank()) return null;
        String at = extract(line, "\"submittedAt\":\"");
        String name = extractJsonString(line, "\"name\":");
        String sugg = extractJsonString(line, "\"suggestion\":");
        if (at == null || name == null || sugg == null) return null;
        try {
            return new Feedback(Instant.parse(at), name, sugg);
        } catch (Exception e) {
            return null;
        }
    }

    private static String extract(String line, String key) {
        int i = line.indexOf(key);
        if (i < 0) return null;
        int start = i + key.length();
        int end = line.indexOf('"', start);
        return end < 0 ? null : line.substring(start, end);
    }

    private static String extractJsonString(String line, String key) {
        int i = line.indexOf(key);
        if (i < 0) return null;
        int p = i + key.length();
        while (p < line.length() && line.charAt(p) != '"') p++;
        if (p >= line.length()) return null;
        p++;
        StringBuilder sb = new StringBuilder();
        while (p < line.length()) {
            char c = line.charAt(p++);
            if (c == '\\' && p < line.length()) {
                char e = line.charAt(p++);
                switch (e) {
                    case '"' -> sb.append('"');
                    case '\\' -> sb.append('\\');
                    case 'n' -> sb.append('\n');
                    case 'r' -> sb.append('\r');
                    case 't' -> sb.append('\t');
                    default -> sb.append(e);
                }
            } else if (c == '"') {
                return sb.toString();
            } else {
                sb.append(c);
            }
        }
        return null;
    }

    private static String quote(String s) {
        if (s == null) return "\"\"";
        StringBuilder sb = new StringBuilder(s.length() + 2);
        sb.append('"');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '"' -> sb.append("\\\"");
                case '\\' -> sb.append("\\\\");
                case '\n' -> sb.append("\\n");
                case '\r' -> sb.append("\\r");
                case '\t' -> sb.append("\\t");
                default -> {
                    if (c < 0x20) sb.append(String.format("\\u%04x", (int) c));
                    else sb.append(c);
                }
            }
        }
        sb.append('"');
        return sb.toString();
    }
}
