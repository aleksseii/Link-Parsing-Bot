package ru.tinkoff.edu.java.bot.telegram_bot.command.enums;

import jakarta.validation.constraints.NotNull;
import org.telegram.telegrambots.meta.api.objects.Update;

public enum CommandType {

    START("/start", "Register user"),
    HELP("/help", "Show list of commands"),
    TRACK("/track", "Start tracking the link"),
    UNTRACK("/untrack", "Stop tracking the link"),
    LIST("/list", "Show list of tracking links");

    private final @NotNull String commandName;

    private final @NotNull String description;

    CommandType(String commandName, String description) {
        this.commandName = commandName;
        this.description = description;
    }

    public boolean supports(Update update) {
        final String text = update.getMessage().getText();
        return text.startsWith(this.commandName);
    }
}
