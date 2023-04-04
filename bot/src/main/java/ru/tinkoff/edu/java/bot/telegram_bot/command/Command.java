package ru.tinkoff.edu.java.bot.telegram_bot.command;

import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public sealed abstract class Command
        permits StartCommand, HelpCommand, TrackCommand, UntrackCommand, ListCommand, EmptyCommand {

    @Setter
    protected @NotNull Command nextCommand;

    public abstract SendMessage handle(@NotNull Update update);

    public abstract boolean supports(@NotNull Update update);
}
