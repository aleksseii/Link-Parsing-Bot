package ru.tinkoff.edu.java.bot.client;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ru.tinkoff.edu.java.bot.dto.client.AddLinkRequest;
import ru.tinkoff.edu.java.bot.dto.client.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.client.ListLinksResponse;
import ru.tinkoff.edu.java.bot.dto.client.RemoveLinkRequest;

public interface ScrapperClient {

    boolean registerChat(@Positive long chatId);

    boolean removeChat(@Positive long chatId);

    @NotNull ListLinksResponse getAllLinks(@Positive long chatId);

    @NotNull LinkResponse addLink(@Positive long chatId, @NotNull AddLinkRequest request);

    @NotNull LinkResponse removeLink(@Positive long chatId, @NotNull RemoveLinkRequest request);
}
