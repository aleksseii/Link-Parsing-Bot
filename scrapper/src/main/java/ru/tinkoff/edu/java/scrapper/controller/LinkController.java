package ru.tinkoff.edu.java.scrapper.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.dto.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.exception.NoSuchLinkException;

import java.util.ArrayList;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/links")
public final class LinkController {

    private static final @NotNull String LINKS_HEADER_NAME = "Tg-Chat-Id";

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public @NotNull ListLinksResponse getAll(@RequestHeader(LINKS_HEADER_NAME) @Positive long chatId) {

        return new ListLinksResponse(new ArrayList<>(), 0);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public @NotNull LinkResponse add(@RequestHeader(LINKS_HEADER_NAME) @Positive long chatId,
                                     @RequestBody @NotNull AddLinkRequest request) {

        return new LinkResponse(chatId, request.url());
    }

    @DeleteMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public @NotNull LinkResponse remove(@RequestHeader(LINKS_HEADER_NAME) @Positive long chatId,
                                        @RequestBody @NotNull RemoveLinkRequest request) {

        throw new NoSuchLinkException(String.format("Such link: `%s` does not exist", request.url()));
    }
}
