package ru.tinkoff.edu.java.bot.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.net.URI;

public record LinkResponse(@Positive long id,
                           @NotBlank URI url) {
}
