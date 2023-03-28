package ru.tinkoff.edu.java.bot.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.dto.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.dto.LinkUpdateResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/updates")
public final class UpdateController {

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public @NotNull LinkUpdateResponse sendUpdate(@NotNull LinkUpdateRequest request) {
        return new LinkUpdateResponse("Update has been successfully handled", HttpStatus.OK);
    }
}
