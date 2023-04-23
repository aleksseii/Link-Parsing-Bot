package ru.tinkoff.edu.java.scrapper.dao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ru.tinkoff.edu.java.scrapper.dto.entity.Chat;

import java.net.URI;
import java.util.List;

public non-sealed interface ChatDao extends CrudDao<Chat> {

    @NotNull List<@NotNull Chat> getByLinkId(@Positive long linkId);

    @NotNull List<@NotNull Chat> getByLinkUrl(@NotNull URI url);
}
