package ru.tinkoff.edu.java.link_parser.parser;

import lombok.AccessLevel;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.tinkoff.edu.java.link_parser.dto.UrlData;

import java.net.URI;

public sealed abstract class Parser permits GitHubParser, StackoverflowParser {

    @Setter(AccessLevel.PROTECTED)
    protected @Nullable Parser nextParser;

    public static @NotNull Parser createChain() {
        Parser headOfChain = new GitHubParser();
        headOfChain.setNextParser(new StackoverflowParser());
        return headOfChain;
    }

    public abstract @Nullable UrlData parse(@NotNull URI url);
}
