package ru.tinkoff.edu.java.bot.configuration.telegram_bot;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.tinkoff.edu.java.bot.telegram_bot.TelegramBot;

@Slf4j
@Component
public final class BotInitializer {

    private final @NotNull TelegramBot bot;

    @Autowired
    public BotInitializer(TelegramBot bot) {
        this.bot = bot;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void init() {

        try {
            TelegramBotsApi tgBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            tgBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            log.error("Could not register bot");
        }
    }
}
