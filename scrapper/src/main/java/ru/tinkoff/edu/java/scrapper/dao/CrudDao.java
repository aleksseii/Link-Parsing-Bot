package ru.tinkoff.edu.java.scrapper.dao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public sealed interface CrudDao<T> permits ChatDao, LinkDao {

    T get(@Positive long id);

    @NotNull List<@NotNull T> getAll();

    void save(@NotNull T element);

    void delete(@Positive long id);
}
