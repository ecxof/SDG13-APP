package com.example.sdg13ver5.model;

import java.time.Instant;

public record Feedback(Instant submittedAt, String name, String suggestion) {}
