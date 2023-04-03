package ru.tinkoff.edu.java.bot.controller.exception_handler;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.java.bot.controller.UpdateController;
import ru.tinkoff.edu.java.bot.dto.ApiErrorResponse;

@RestControllerAdvice(basePackageClasses = UpdateController.class)
public final class BotExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public @NotNull ApiErrorResponse handleIllegalArgument(@NotNull IllegalArgumentException exception) {

        return new ApiErrorResponse("Invalid request parameters", HttpStatus.BAD_REQUEST, exception);
    }
}
