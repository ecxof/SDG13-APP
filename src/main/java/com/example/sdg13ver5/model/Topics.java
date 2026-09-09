package com.example.sdg13ver5.model;

import java.util.List;
import java.util.Optional;

public final class Topics {

    private Topics() {}

    public static final List<Topic> ALL = List.of(
            new Topic(1,
                    "Improve Education",
                    "Empowering the next generation with Climate Change Education (CCE).",
                    "Comprehensive Climate Education",
                    "fth-book-open",
                    "#0EA5E9",
                    "page1.txt",
                    "Climate Change Education supports SDG-13 by building awareness and skills from early childhood to secondary school, teaching climate science, mitigation, adaptation, and sustainable behaviors, with a focus on vulnerable communities."),
            new Topic(2,
                    "Awareness Raising",
                    "Building public understanding of climate change impacts and solutions.",
                    "Awareness Raising",
                    "fth-radio",
                    "#F59E0B",
                    "page2.txt",
                    "Climate change is intensifying extreme weather, harming food security, health, infrastructure, and basic services. Since 1880, global temperatures rose 0.85°C, sea levels increased 19 cm, Arctic ice declined, and CO₂ emissions rose nearly 50% since 1990."),
            new Topic(3,
                    "Human Impact",
                    "Exploring how climate change affects human health and communities.",
                    "Human Impact",
                    "fth-users",
                    "#EF4444",
                    "page3.txt",
                    "SDG-13 and SDG-3 are closely linked, as climate change threatens health through extreme weather, disease spread, and social instability. Cutting emissions by 2030 can limit warming to 1.5°C while delivering major health, economic, and societal benefits."),
            new Topic(4,
                    "Adaptation",
                    "Strengthening resilience by preparing communities for climate change.",
                    "Adaptation",
                    "fth-refresh-cw",
                    "#10B981",
                    "page4.txt",
                    "Climate change is a cross-cutting issue requiring urgent action. Mozambique is proactively addressing it through coordinated climate groups and support. Sustainability, understood as long-term resource management within social–ecological systems, is central to effective climate adaptation."),
            new Topic(5,
                    "Impact Reduction",
                    "Reducing emissions and environmental harm across sectors.",
                    "Impact Reduction",
                    "fth-shield",
                    "#8B5CF6",
                    "page5.txt",
                    "Although often overlooked, aviation significantly contributes to climate change through CO₂ emissions and high-altitude contrails. Mitigation options include airspace reconfiguration, vertical sector adjustments, and lowering cruising altitudes to reduce climate impacts."),
            new Topic(6,
                    "Early Warning",
                    "Detecting and responding to climate hazards early.",
                    "Early Warning",
                    "fth-alert-triangle",
                    "#F97316",
                    "page6.txt",
                    "Throughout history, catastrophic events have occurred when the Earth reached critical tipping points. Studying past extreme climate events provides early warnings, as once-rare incidents are becoming common, allowing patterns to be identified to help prevent or mitigate future climate disasters.")
    );

    public static Topic byId(int id) {
        return ALL.stream().filter(t -> t.id() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown topic id: " + id));
    }

    public static Optional<Topic> search(String query) {
        if (query == null || query.isBlank()) return Optional.empty();
        String q = query.toLowerCase().trim();
        return ALL.stream()
                .filter(t -> t.title().toLowerCase().contains(q)
                        || t.cardHeading().toLowerCase().contains(q)
                        || t.subtitle().toLowerCase().contains(q))
                .findFirst();
    }

    public static List<Topic> filter(String query) {
        if (query == null || query.isBlank()) return ALL;
        String q = query.toLowerCase().trim();
        return ALL.stream()
                .filter(t -> t.title().toLowerCase().contains(q)
                        || t.cardHeading().toLowerCase().contains(q)
                        || t.subtitle().toLowerCase().contains(q))
                .toList();
    }
}
