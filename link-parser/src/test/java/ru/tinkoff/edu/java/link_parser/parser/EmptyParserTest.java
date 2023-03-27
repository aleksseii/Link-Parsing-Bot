package ru.tinkoff.edu.java.link_parser.parser;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.link_parser.dto.UrlData;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class EmptyParserTest {

    private static final @NotNull EmptyParser PARSER = new EmptyParser();

    private static final @NotNull String RANDOM_LINK = "https://random/link";

    @Test
    void shouldAlwaysReturnNull() {
        final UrlData actual = PARSER.parse(URI.create(RANDOM_LINK));
        assertNull(actual);
    }
}