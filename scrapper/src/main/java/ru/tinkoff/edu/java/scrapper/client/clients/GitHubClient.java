package ru.tinkoff.edu.java.scrapper.client.clients;

import jakarta.validation.constraints.NotBlank;
import ru.tinkoff.edu.java.scrapper.client.dto.GitHubRepoResponse;

public sealed interface GitHubClient permits GitHubClientImpl {

    GitHubRepoResponse getRepo(@NotBlank String ownerName, @NotBlank String repoName);
}
