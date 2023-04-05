package ru.tinkoff.edu.java.bot.telegram_bot;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.bot.telegram_bot.command.enums.AllCommandTypes;
import ru.tinkoff.edu.java.bot.telegram_bot.command.enums.CommandType;

import java.util.List;

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

    @PostConstruct
    public void initializeMenu() {

        List<BotCommand> botCommands = AllCommandTypes.get().stream()
                .map(CommandType::toBotCommand)
                .toList();

        SetMyCommands setMyCommands = new SetMyCommands(
                botCommands,
                new BotCommandScopeDefault(),
                null
        );
        try {
            execute(setMyCommands);
        } catch (TelegramApiException e) {
            log.error(e.toString());
        }
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
