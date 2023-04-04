package ru.tinkoff.edu.java.bot.telegram_bot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public final class HelpCommand extends Command {

    @Override
    public SendMessage handle(Update update) {
        return null;
    }

    @Override
    public boolean supports(Update update) {
        return false;
    }
}
