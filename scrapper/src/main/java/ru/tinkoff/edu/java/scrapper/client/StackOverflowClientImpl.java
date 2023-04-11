package ru.tinkoff.edu.java.scrapper.client;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.client.StackOverflowQuestionResponse;

public final class StackOverflowClientImpl implements StackOverflowClient {

    private static final @NotNull String BASE_URL = "https://api.stackexchange.com/2.3";

    private static final @NotNull String QUESTION_URL = "/questions/{id}";

    private final @NotNull String baseUrl;

    private WebClient webClient;

    public StackOverflowClientImpl() {
        this(BASE_URL);
    }

    public StackOverflowClientImpl(@NotNull String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @PostConstruct
    public void startWebClient() {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Override
    public StackOverflowQuestionResponse getQuestion(@Positive long questionId) {

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(QUESTION_URL)
                        .queryParam("site", "stackoverflow")
                        .build(questionId))
                .retrieve()
                .bodyToMono(StackOverflowQuestionResponse.class)
                .block();
    }
}
