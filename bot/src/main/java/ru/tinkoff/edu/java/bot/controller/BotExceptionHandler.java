package ru.tinkoff.edu.java.bot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.java.bot.dto.ApiErrorResponse;

@RestControllerAdvice(basePackageClasses = UpdateController.class)
public final class BotExceptionHandler {

    @ExceptionHandler
    public ApiErrorResponse handleBadRequest(IllegalArgumentException exception) {
        return new ApiErrorResponse("Invalid request parameters", HttpStatus.BAD_REQUEST, exception);
    }
}
