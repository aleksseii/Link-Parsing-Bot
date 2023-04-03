package ru.tinkoff.edu.java.scrapper.exception;

import jakarta.validation.constraints.NotBlank;

public final class NoSuchLinkException extends RuntimeException {

    public NoSuchLinkException(@NotBlank String message) {
        super(message);
    }
}
