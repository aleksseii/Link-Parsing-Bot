package ru.tinkoff.edu.java.bot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public record ApiErrorResponse(@NotNull String description,
                               @NotNull String statusCode,
                               @NotNull Class<? extends Exception> exceptionName,
                               @NotBlank String exceptionMessage,
                               @NotNull List<@NotBlank String> stacktrace) {

    public ApiErrorResponse(@NotNull String description,
                            @NotNull HttpStatus statusCode,
                            @NotNull Exception exception) {
        this(
                description,
                statusCode.toString(),
                exception.getClass(),
                exception.getMessage(),
                Arrays.stream(exception.getStackTrace())
                        .map(StackTraceElement::toString)
                        .toList()
        );
    }
}
