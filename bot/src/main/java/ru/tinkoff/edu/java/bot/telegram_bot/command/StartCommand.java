package ru.tinkoff.edu.java.bot.telegram_bot.command;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.telegram_bot.command.enums.CommandType;

@Slf4j
@Component
public final class StartCommand extends Command {

    private static final @NotNull CommandType TYPE = CommandType.START;

    private static final @NotNull String REGISTER_USER_RESPONSE_TEXT = "Registering new user";

    private final @NotNull ScrapperClient client;

    @Autowired
    public StartCommand(@NotNull ScrapperClient client) {
        this.client = client;
    }

    @Override
    public @NotNull SendMessage handle(@NotNull Update update) {
        if (supports(update)) {
            SendMessage response = new SendMessage();
            response.setText(REGISTER_USER_RESPONSE_TEXT);

            long chatId = update.getMessage().getChatId();
            boolean registered = client.registerChat(chatId);
            logRegistration(registered);

            return response;
        }

        return nextCommand.handle(update);
    }

    @Override
    public boolean supports(@NotNull Update update) {
        return TYPE.supports(update);
    }

    private static void logRegistration(boolean registered) {
        if (registered) {
            log.info("Registered new user");
        } else {
            log.error("Failed registering new user");
        }
    }
}
