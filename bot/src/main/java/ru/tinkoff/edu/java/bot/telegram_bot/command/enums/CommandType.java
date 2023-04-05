package ru.tinkoff.edu.java.bot.telegram_bot.command.enums;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;

public enum CommandType {

    START("/start", "Register user"),
    HELP("/help", "Show list of commands"),
    TRACK("/track <link>", "Start tracking the link"),
    UNTRACK("/untrack <link>", "Stop tracking the link"),
    LIST("/list", "Show list of tracking links");

    @Getter
    private final @NotNull String commandName;

    @Getter
    private final @NotNull String description;

    CommandType(@NotNull String commandName, @NotNull String description) {
        this.commandName = commandName;
        this.description = description;
    }

    public boolean supports(@NotNull Update update) {
        final String text = update.getMessage().getText();
        return text.startsWith("/" + this.name().toLowerCase());
    }
}
