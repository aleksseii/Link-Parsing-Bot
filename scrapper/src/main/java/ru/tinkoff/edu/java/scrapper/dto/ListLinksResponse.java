package ru.tinkoff.edu.java.scrapper.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;


import java.util.List;

public record ListLinksResponse(@NotNull List<@NotNull LinkResponse> links,
                                @PositiveOrZero int size) {
}
