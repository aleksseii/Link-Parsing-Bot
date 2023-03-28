package ru.tinkoff.edu.java.scrapper.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.net.URI;

public record LinkResponse(@Positive long id,
                           @NotBlank URI url) {
}
