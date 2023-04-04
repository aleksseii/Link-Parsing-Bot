package ru.tinkoff.edu.java.bot.telegram_bot.command;

import jakarta.validation.constraints.NotNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.telegram_bot.command.enums.CommandType;

public final class StartCommand extends Command {

    private static final @NotNull CommandType TYPE = CommandType.START;

    @Override
    public SendMessage handle(@NotNull Update update) {
        return null;
    }

    @Override
    public boolean supports(@NotNull Update update) {
        return TYPE.supports(update);
    }
}
