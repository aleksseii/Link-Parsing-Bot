package ru.tinkoff.edu.java.link_parser.parser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.tinkoff.edu.java.link_parser.dto.GitHubData;
import ru.tinkoff.edu.java.link_parser.dto.UrlData;

import java.net.URI;

public final class GitHubParser extends Parser {

    private static final @NotNull String GITHUB_PREFIX = "https://github.com/";

    private static final @NotNull String SLASH = "/";

    @Override
    public @Nullable UrlData parse(@NotNull URI url) {

        final String urlString = url.toString();
        if (urlString.startsWith(GITHUB_PREFIX)) {
            String[] pair = urlString
                    .substring(GITHUB_PREFIX.length())
                    .split(SLASH);
            if (pair.length >= 2) {
                return new GitHubData(pair[0], pair[1]);
            }
        }
        return nextParser.parse(url);
    }
}
