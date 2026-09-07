package com.example.sdg13ver5.model;

public record Topic(
        int id,
        String title,
        String subtitle,
        String cardHeading,
        String iconLiteral,
        String accentColor,
        String dataFileName,
        String defaultBody) {

    public String displayLabel() {
        return "Page " + id + " — " + title;
    }
}
