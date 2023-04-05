package ru.tinkoff.edu.java.bot.configuration.client;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.client.ScrapperClientImpl;

@Validated
@Configuration
public class ClientConfiguration {

    private final @NotNull String scrapperBaseUrl;

    public ClientConfiguration(@Value("${client.base-url.scrapper}") @NotNull String scrapperBaseUrl) {
        this.scrapperBaseUrl = scrapperBaseUrl;
    }

    @Bean
    public ScrapperClient scrapperClient() {
        return new ScrapperClientImpl(scrapperBaseUrl);
    }
}
