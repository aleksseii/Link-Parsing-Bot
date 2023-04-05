package ru.tinkoff.edu.java.bot.telegram_bot.command.enums;

import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

import static ru.tinkoff.edu.java.bot.telegram_bot.command.enums.CommandType.*;

public final class AllCommandTypes {

    private static final List<@NotNull CommandType> COMMAND_TYPES = List.of(
            START,
            HELP,
            TRACK,
            UNTRACK,
            LIST
    );

    public static @NotNull List<@NotNull CommandType> get() {
        return COMMAND_TYPES;
    }

    public static @NotNull List<@NotNull CommandType> getModifiable() {
        return new ArrayList<>(COMMAND_TYPES);
    }
}
