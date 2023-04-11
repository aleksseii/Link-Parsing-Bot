package ru.tinkoff.edu.java.scrapper.client;

import jakarta.validation.constraints.NotBlank;
import ru.tinkoff.edu.java.scrapper.dto.client.GitHubRepoResponse;

public sealed interface GitHubClient permits GitHubClientImpl {

    GitHubRepoResponse getRepo(@NotBlank String ownerName, @NotBlank String repoName);
}
