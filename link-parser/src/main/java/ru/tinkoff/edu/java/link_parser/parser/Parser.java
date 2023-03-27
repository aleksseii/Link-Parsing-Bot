package ru.tinkoff.edu.java.link_parser.parser;

import lombok.AccessLevel;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.tinkoff.edu.java.link_parser.dto.UrlData;

import java.net.URI;

public sealed abstract class Parser permits GitHubParser, StackOverflowParser, EmptyParser {

    @Setter(AccessLevel.PUBLIC)
    protected @NotNull Parser nextParser; // the only nullable parser is instance of EmptyParser

    public abstract @Nullable UrlData parse(@NotNull URI url);
}
