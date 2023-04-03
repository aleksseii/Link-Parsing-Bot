package ru.tinkoff.edu.java.scrapper.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public record ApiErrorResponse(@NotNull String description,
                               @NotBlank String statusCode,
                               Class<? extends Exception> exceptionName,
                               String exceptionMessage,
                               List<@NotBlank String> stacktrace) {

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

    public ApiErrorResponse(@NotNull String description,
                            @NotBlank HttpStatus statusCode) {
        this(
                description,
                statusCode.toString(),
                null,
                null,
                null
        );
    }
}
