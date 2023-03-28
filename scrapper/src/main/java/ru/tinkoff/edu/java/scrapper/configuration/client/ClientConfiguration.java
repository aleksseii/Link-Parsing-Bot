package ru.tinkoff.edu.java.scrapper.configuration.client;

import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.java.scrapper.client.clients.GitHubClient;
import ru.tinkoff.edu.java.scrapper.client.clients.GitHubClientImpl;
import ru.tinkoff.edu.java.scrapper.client.clients.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.client.clients.StackOverflowClientImpl;

@Validated
@Configuration
public class ClientConfiguration {

    private final @NotBlank String gitHubBaseUrl;
    private final @NotBlank String stackOverflowBaseUrl;

    public ClientConfiguration(@Value("$client.base-url.github") @NotBlank String gitHubBaseUrl,
                               @Value("$client.base-url.stackoverflow") @NotBlank String stackOverflowBaseUrl) {

        this.gitHubBaseUrl = gitHubBaseUrl;
        this.stackOverflowBaseUrl = stackOverflowBaseUrl;
    }

    @Bean
    public GitHubClient getGitHubClient() {
        return new GitHubClientImpl(gitHubBaseUrl);
    }

    @Bean
    public StackOverflowClient getStackOverflowClient() {
        return new StackOverflowClientImpl(stackOverflowBaseUrl);
    }
}
