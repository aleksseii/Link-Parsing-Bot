package ru.tinkoff.edu.java.bot.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@Validated
@ConfigurationProperties(prefix = "bot", ignoreUnknownFields = false)
public record ApplicationConfig(@NotNull String username,
                                @NotNull String token) {
}
