package ru.tinkoff.edu.java.scrapper.dto;

import jakarta.validation.constraints.PositiveOrZero;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record ListLinksResponse(@NotNull List<@NotNull LinkResponse> links,
                                @PositiveOrZero int size) {
}
