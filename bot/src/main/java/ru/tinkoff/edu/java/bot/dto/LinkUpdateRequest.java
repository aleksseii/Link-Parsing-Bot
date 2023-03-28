package ru.tinkoff.edu.java.bot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.net.URI;
import java.util.List;

public record LinkUpdateRequest(@Positive long id,
                                @NotNull URI url,
                                @NotBlank String description,
                                @NotNull List<@NotNull @Positive Long> tgChatIds) {
}
