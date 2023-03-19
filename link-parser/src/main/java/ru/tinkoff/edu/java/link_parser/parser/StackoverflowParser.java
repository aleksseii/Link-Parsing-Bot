package ru.tinkoff.edu.java.link_parser.parser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.tinkoff.edu.java.link_parser.dto.StackOverflowData;
import ru.tinkoff.edu.java.link_parser.dto.UrlData;

import java.net.URI;

public final class StackoverflowParser extends Parser {

    private static final @NotNull String STACKOVERFLOW_QUESTIONS_PREFIX =
            "https://stackoverflow.com/questions/";

    private static final @NotNull String SLASH = "/";

    @Override
    public @Nullable UrlData parse(@NotNull URI url) {

        final String urlString = url.toString();
        if (urlString.startsWith(STACKOVERFLOW_QUESTIONS_PREFIX)) {
            String[] values = urlString
                    .substring(STACKOVERFLOW_QUESTIONS_PREFIX.length())
                    .split(SLASH);
            try {
                long id = Long.parseLong(values[0]);
                return new StackOverflowData(id);
            } catch (NumberFormatException var6) {
                return null;
            }
        }
        return nextParser == null ? null : nextParser.parse(url);
    }
}
