package ru.tinkoff.edu.java.bot.client;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.dto.client.AddLinkRequest;
import ru.tinkoff.edu.java.bot.dto.client.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.client.ListLinksResponse;
import ru.tinkoff.edu.java.bot.dto.client.RemoveLinkRequest;

public final class ScrapperClientImpl implements ScrapperClient {

    private static final @NotNull String BASE_URL = "localhost:8081";

    private static final @NotNull String TG_CHAT_URL = "/tg-chat/{id}";
    private static final @NotNull String LINKS_URL = "/links";
    private static final @NotNull String LINKS_REQUEST_HEADER_NAME = "Tg-Chat-Id";

    private final @NotNull String baseUrl;

    private WebClient webClient;

    public ScrapperClientImpl() {
        this(BASE_URL);
    }

    public ScrapperClientImpl(@NotNull String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @PostConstruct
    public void startWebClient() {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Override
    public boolean registerChat(@Positive long chatId) {

        final Boolean result = webClient
                .post()
                .uri(TG_CHAT_URL, chatId)
                .retrieve()
                .toBodilessEntity()
                .flatMap(response -> Mono.just(
                        response
                                .getStatusCode()
                                .is2xxSuccessful()
                ))
                .block();
        return Boolean.TRUE.equals(result);
    }

    @Override
    public boolean removeChat(@Positive long chatId) {

        final Boolean result = webClient
                .delete()
                .uri(TG_CHAT_URL, chatId)
                .retrieve()
                .toBodilessEntity()
                .flatMap(response -> Mono.just(
                        response
                                .getStatusCode()
                                .is2xxSuccessful()
                ))
                .block();
        return Boolean.TRUE.equals(result);
    }

    @Override
    public @NotNull ListLinksResponse getAllLinks(@Positive long chatId) {

        return webClient
                .get()
                .uri(LINKS_URL)
                .header(LINKS_REQUEST_HEADER_NAME, String.valueOf(chatId))
                .retrieve()
                .bodyToMono(ListLinksResponse.class)
                .block();
    }

    @Override
    public @NotNull LinkResponse addLink(@Positive long chatId,
                                         @NotNull AddLinkRequest request) {

        return webClient
                .post()
                .uri(LINKS_URL)
                .header(LINKS_REQUEST_HEADER_NAME, String.valueOf(chatId))
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .block();
    }

    @Override
    public @NotNull LinkResponse removeLink(@Positive long chatId,
                                            @NotNull RemoveLinkRequest request) {

        return webClient
                .method(HttpMethod.DELETE)
                .uri(LINKS_URL)
                .header(LINKS_REQUEST_HEADER_NAME, String.valueOf(chatId))
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .block();
    }
}
