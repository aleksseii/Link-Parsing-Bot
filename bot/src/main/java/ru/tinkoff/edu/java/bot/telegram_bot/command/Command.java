package ru.tinkoff.edu.java.bot.telegram_bot.command;

import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public sealed abstract class Command
        permits StartCommand, HelpCommand, TrackCommand, UntrackCommand, ListCommand, EmptyCommand {

    @Setter
    protected Command nextCommand;

    public abstract SendMessage handle(Update update);

    public abstract boolean supports(Update update);
}
