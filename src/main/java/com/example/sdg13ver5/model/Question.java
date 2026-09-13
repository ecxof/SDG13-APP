package com.example.sdg13ver5.model;

import java.util.List;

public record Question(String prompt, List<String> choices, int correctIndex, String explanation) {

    public boolean isCorrect(int chosen) {
        return chosen == correctIndex;
    }
}
