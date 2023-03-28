package ru.tinkoff.edu.java.scrapper.scheduler;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record Scheduler(@NotNull Duration interval) {
}
