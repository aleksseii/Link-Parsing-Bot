package ru.tinkoff.edu.java.scrapper.configuration.client;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.java.scrapper.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.client.GitHubClientImpl;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClientImpl;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;

@Validated
@Configuration
public class ClientConfiguration {

    private final @NotNull String gitHubBaseUrl;
    private final @NotNull String stackOverflowBaseUrl;

    public ClientConfiguration(@Value("$client.base-url.github") @NotNull String gitHubBaseUrl,
                               @Value("$client.base-url.stackoverflow") @NotNull String stackOverflowBaseUrl) {

        this.gitHubBaseUrl = gitHubBaseUrl;
        this.stackOverflowBaseUrl = stackOverflowBaseUrl;
    }

    @Bean
    public @NotNull GitHubClient getGitHubClient() {
        return new GitHubClientImpl(gitHubBaseUrl);
    }

    @Bean
    public @NotNull StackOverflowClient getStackOverflowClient() {
        return new StackOverflowClientImpl(stackOverflowBaseUrl);
    }

    @Bean
    public long schedulerIntervalMillis(@NotNull ApplicationConfig config) {
        return config.scheduler().interval().toMillis();
    }
}
