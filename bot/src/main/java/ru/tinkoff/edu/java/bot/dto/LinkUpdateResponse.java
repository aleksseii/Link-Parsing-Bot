package ru.tinkoff.edu.java.bot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

public record LinkUpdateResponse(@NotBlank String message,
                                 @NotNull HttpStatus statusCode) {
}
