package ru.tinkoff.edu.java.link_parser.parser;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.link_parser.dto.GitHubData;
import ru.tinkoff.edu.java.link_parser.dto.StackOverflowData;
import ru.tinkoff.edu.java.link_parser.dto.UrlData;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ParserTest {

    private static final @NotNull Parser CHAIN = Parser.createChain();

    private static final String EXPECTED_USER_NAME = "aleksseii";
    private static final String EXPECTED_REPO_NAME = "LibraryManager-Backend";
    private static final @NotNull String VALID_GITHUB_LINK =
            "https://github.com/aleksseii/LibraryManager-Backend/tree/master/src/main/java/ru/aleksseii/library_manager_backend";

    private static final long EXPECTED_QUESTION_ID = 1642028L;
    private static final @NotNull String VALID_STACKOVERFLOW_LINK =
            "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c";

    private static final @NotNull String INVALID_LINK = "invalid/link/or/not/a/link/at/all";

    @Test
    void shouldParseValidGitHubLink() {
        UrlData expected = new GitHubData(EXPECTED_USER_NAME, EXPECTED_REPO_NAME);
        UrlData actual = CHAIN.parse(URI.create(VALID_GITHUB_LINK));
        assertEquals(expected, actual);
    }

    @Test
    void shouldParseValidStackOverflowLink() {
        UrlData expected = new StackOverflowData(EXPECTED_QUESTION_ID);
        UrlData actual = CHAIN.parse(URI.create(VALID_STACKOVERFLOW_LINK));
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnNullGettingInvalidLink() {
        UrlData actual = CHAIN.parse(URI.create(INVALID_LINK));
        assertNull(actual);
    }
}