package ru.tinkoff.edu.java.link_parser.parser;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.link_parser.dto.StackOverflowData;
import ru.tinkoff.edu.java.link_parser.dto.UrlData;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StackOverflowParserTest {

    private static final @NotNull StackOverflowParser PARSER = new StackOverflowParser();

    private static final long EXPECTED_QUESTION_ID = 1642028L;

    private static final @NotNull String VALID_STACKOVERFLOW_LINK =
            "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c";

    private static final @NotNull String INVALID_STACKOVERFLOW_LINK =
            "https://stackoverflow.com/";

    private static final @NotNull String INVALID_ID_STACKOVERFLOW_LINK =
            "https://stackoverflow.com/questions/abcdef/what-is-the-operator-in-c";


    @Test
    void shouldParseValidStackOverflowLink() {
        final UrlData expected = new StackOverflowData(EXPECTED_QUESTION_ID);
        final UrlData actual = PARSER.parse(URI.create(VALID_STACKOVERFLOW_LINK));
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnNullGettingInvalidLink() {
        final UrlData actual = PARSER.parse(URI.create(INVALID_STACKOVERFLOW_LINK));
        assertNull(actual);
    }

    @Test
    void shouldReturnNullGettingInvalidIdLink() {
        final UrlData actual = PARSER.parse(URI.create(INVALID_ID_STACKOVERFLOW_LINK));
        assertNull(actual);
    }
}
