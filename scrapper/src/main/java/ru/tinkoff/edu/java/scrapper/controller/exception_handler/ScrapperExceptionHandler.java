package ru.tinkoff.edu.java.scrapper.controller.exception_handler;


import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.java.scrapper.controller.LinkController;
import ru.tinkoff.edu.java.scrapper.controller.TgChatController;
import ru.tinkoff.edu.java.scrapper.dto.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.exception.NoSuchChatException;
import ru.tinkoff.edu.java.scrapper.exception.NoSuchLinkException;

@RestControllerAdvice(basePackageClasses = { TgChatController.class, LinkController.class })
public final class ScrapperExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public @NotNull ApiErrorResponse handleIllegalArgument(@NotNull IllegalArgumentException exception) {
        return new ApiErrorResponse("Invalid request parameters", HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler(NoSuchChatException.class)
    public @NotNull ApiErrorResponse handleNoSuchChat(@NotNull NoSuchChatException exception) {
        return new ApiErrorResponse("Chat does not exist", HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler(NoSuchLinkException.class)
    public @NotNull ApiErrorResponse handleNoSuchLink(@NotNull NoSuchLinkException exception) {
        return new ApiErrorResponse("Link does not exist", HttpStatus.NOT_FOUND, exception);
    }
}
