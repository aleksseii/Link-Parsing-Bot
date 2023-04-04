package ru.tinkoff.edu.java.bot.telegram_bot.command;

import jakarta.validation.constraints.NotNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public final class EmptyCommand extends Command {

    @Override
    public SendMessage handle(@NotNull Update update) {
        return null;
    }

    @Override
    public boolean supports(@NotNull Update update) {
        return true;
    }
}
