package ru.tinkoff.edu.java.scrapper.dao.impl;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ru.tinkoff.edu.java.scrapper.dao.LinkDao;
import ru.tinkoff.edu.java.scrapper.dto.entity.Link;

import java.util.ArrayList;
import java.util.List;

// TODO: implement methods
public final class LinkDaoImpl implements LinkDao {

    @Override
    public Link get(long id) {
        return null;
    }

    @Override
    public @NotNull List<@NotNull Link> getAll() {
        return new ArrayList<>();
    }

    @Override
    public void save(@NotNull Link element) {

    }

    @Override
    public void delete(@Positive long id) {

    }
}
