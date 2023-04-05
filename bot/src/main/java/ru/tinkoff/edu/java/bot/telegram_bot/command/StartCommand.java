package ru.tinkoff.edu.java.bot.telegram_bot.command;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.telegram_bot.command.enums.CommandType;

@Slf4j
public final class StartCommand extends Command {

    private static final @NotNull CommandType TYPE = CommandType.START;

    private static final @NotNull String REGISTER_NEW_USER_RESPONSE_TEXT = "Registering new user";

    @Override
    public SendMessage handle(@NotNull Update update) {
        if (supports(update)) {
            SendMessage response = new SendMessage();
            response.setText(REGISTER_NEW_USER_RESPONSE_TEXT);

            log.info("Registered new user");
            return response;
        }

        return nextCommand.handle(update);
    }

    @Override
    public boolean supports(@NotNull Update update) {
        return TYPE.supports(update);
    }
}
