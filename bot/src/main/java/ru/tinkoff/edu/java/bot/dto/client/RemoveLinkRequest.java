package ru.tinkoff.edu.java.bot.dto.client;

import jakarta.validation.constraints.NotBlank;

import java.net.URI;

public record RemoveLinkRequest(@NotBlank URI url) {
}
