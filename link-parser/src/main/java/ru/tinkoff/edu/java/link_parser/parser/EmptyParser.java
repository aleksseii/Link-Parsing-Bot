package ru.tinkoff.edu.java.link_parser.parser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.tinkoff.edu.java.link_parser.dto.UrlData;

import java.net.URI;

public final class EmptyParser extends Parser {

    @Override
    public @Nullable UrlData parse(@NotNull URI url) {
        return null;
    }
}
