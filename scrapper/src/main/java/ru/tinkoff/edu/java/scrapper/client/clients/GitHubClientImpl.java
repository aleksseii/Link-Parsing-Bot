package ru.tinkoff.edu.java.scrapper.client.clients;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.client.dto.GitHubRepoResponse;

public final class GitHubClientImpl implements GitHubClient {

    private static final @NotNull String BASE_URL = "https://api.github.com";

    private static final @NotNull String REPO_URL = "/repos/{owner}/{repo}";

    private final @NotNull String baseUrl;

    private WebClient webClient;

    public GitHubClientImpl() {
        this(BASE_URL);
    }

    public GitHubClientImpl(@NotNull String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @PostConstruct
    public void startWebClient() {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Override
    public GitHubRepoResponse getRepo(@NotBlank String ownerName, @NotBlank String repoName) {

        return webClient
                .get()
                .uri(REPO_URL, ownerName, repoName)
                .retrieve()
                .bodyToMono(GitHubRepoResponse.class)
                .block();
    }
}
