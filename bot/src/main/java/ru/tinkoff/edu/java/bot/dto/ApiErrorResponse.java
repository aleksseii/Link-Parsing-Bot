package ru.tinkoff.edu.java.bot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.List;

public record ApiErrorResponse(@NotNull String description,
                               @NotNull String code,
                               @NotNull Class<? extends Exception> exceptionName,
                               @NotBlank String exceptionMessage,
                               @NotNull List<@NotBlank String> stacktrace) {

    public ApiErrorResponse(@NotNull String description,
                            @NotNull String code,
                            @NotNull Exception exception) {
        this(
                description,
                code,
                exception.getClass(),
                exception.getMessage(),
                Arrays.stream(exception.getStackTrace())
                        .map(StackTraceElement::toString)
                        .toList()
        );
    }
}
