package ru.tinkoff.edu.java.scrapper.exception;

import jakarta.validation.constraints.NotBlank;

public final class NoSuchChatException extends RuntimeException {

    public NoSuchChatException(@NotBlank String message) {
        super(message);
    }
}
