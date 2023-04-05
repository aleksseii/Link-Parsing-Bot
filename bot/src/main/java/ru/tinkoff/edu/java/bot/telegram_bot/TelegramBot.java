package ru.tinkoff.edu.java.bot.telegram_bot;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;

@Slf4j
@Component
public final class TelegramBot extends TelegramLongPollingBot {

    private final @NotNull ApplicationConfig config;

    private final @NotNull UpdateHandler updateHandler;

    @Autowired
    public TelegramBot(ApplicationConfig config, UpdateHandler updateHandler) {
        super(config.token());
        this.config = config;
        this.updateHandler = updateHandler;
    }

    @Override
    public String getBotUsername() {
        return config.username();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            SendMessage response = updateHandler.handle(update);
            sendResponse(response);
        }
    }

    private void sendResponse(@NotNull SendMessage response) {
        try {
            execute(response);
        } catch (TelegramApiException e) {
            log.error(e.toString());
        }
    }
}
