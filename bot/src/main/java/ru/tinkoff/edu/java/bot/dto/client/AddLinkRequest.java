package ru.tinkoff.edu.java.bot.dto.client;

import jakarta.validation.constraints.NotBlank;

import java.net.URI;

public record AddLinkRequest(@NotBlank URI url) {
}
