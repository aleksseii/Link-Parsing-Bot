package ru.tinkoff.edu.java.bot.telegram_bot.command;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public final class UnsupportedCommand extends Command {

    private static final @NotNull String UNSUPPORTED_COMMAND_RESPONSE_TEXT =
            "This command is not supported.\r\nType `/help` for more info";

    @Override
    public @NotNull SendMessage handle(@NotNull Update update) {

        SendMessage response = new SendMessage();
        response.setText(UNSUPPORTED_COMMAND_RESPONSE_TEXT);

        log.info("Received unsupported command");
        return response;
    }

    @Override
    public boolean supports(@NotNull Update update) {
        return true;
    }
}
