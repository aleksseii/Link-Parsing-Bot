package ru.tinkoff.edu.java.scrapper.dao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ru.tinkoff.edu.java.scrapper.dto.entity.Link;

import java.net.URI;
import java.util.List;

public non-sealed interface LinkDao extends CrudDao<Link> {

    Link getByUrl(@NotNull URI url);

    @NotNull List<@NotNull Link> getByChatId(@Positive long chatId);

    void delete(@NotNull URI url);
}
