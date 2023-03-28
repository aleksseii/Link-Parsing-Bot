package ru.tinkoff.edu.java.scrapper.client.clients;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.client.dto.GitHubRepoResponse;

public final class GitHubClientImpl implements GitHubClient {

    private static final @NotBlank String BASE_URL = "https://api.github.com";

    private static final @NotBlank String REPO_URL = "/repos/{owner}/{repo}";

    private final @NotBlank String baseUrl;

    private WebClient webClient;

    public GitHubClientImpl() {
        this(BASE_URL);
    }

    public GitHubClientImpl(@NotBlank String baseUrl) {
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
