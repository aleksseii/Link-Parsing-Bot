package ru.tinkoff.edu.java.link_parser.parser;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.link_parser.dto.GitHubData;
import ru.tinkoff.edu.java.link_parser.dto.UrlData;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GitHubParserTest {

    private static final @NotNull GitHubParser PARSER = new GitHubParser();

    private static final String EXPECTED_USER_NAME = "aleksseii";
    private static final String EXPECTED_REPO_NAME = "LibraryManager-Backend";

    private static final @NotNull String VALID_GITHUB_LINK =
            "https://github.com/aleksseii/LibraryManager-Backend/tree/master/src/main/java/ru/aleksseii/library_manager_backend";

    private static final @NotNull String INVALID_GITHUB_LINK = "https://github.com/aleksseii/";

    @Test
    void shouldParseValidGitHubLink() {
        final UrlData expected = new GitHubData(EXPECTED_USER_NAME, EXPECTED_REPO_NAME);
        final UrlData actual = PARSER.parse(URI.create(VALID_GITHUB_LINK));
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnNullGettingInvalidLink() {
        final UrlData actual = PARSER.parse(URI.create(INVALID_GITHUB_LINK));
        assertNull(actual);
    }
}