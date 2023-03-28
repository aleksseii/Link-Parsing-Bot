package ru.tinkoff.edu.java.scrapper.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.LinkResponse;
import ru.tinkoff.edu.java.scrapper.exception.NoSuchChatException;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/tg-chat")
public final class TgChatController {

    @PostMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public @NotNull LinkResponse register(@PathVariable(name = "id") @Positive long chatId) {

        return new LinkResponse(chatId, URI.create("/stub"));
    }

    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public @NotNull LinkResponse remove(@PathVariable(name = "id") @Positive long chatId) {

        throw new NoSuchChatException(String.format("Chat with id = %d does not exist", chatId));
    }
}
