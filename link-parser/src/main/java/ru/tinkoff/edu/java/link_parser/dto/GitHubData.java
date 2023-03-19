package ru.tinkoff.edu.java.link_parser.dto;

import org.jetbrains.annotations.NotNull;

public record GitHubData(@NotNull String userName,
                         @NotNull String repositoryName) implements UrlData {
}
