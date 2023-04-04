package ru.tinkoff.edu.java.bot.telegram_bot.command.factory;

import jakarta.validation.constraints.NotNull;
import ru.tinkoff.edu.java.bot.telegram_bot.command.*;

public final class CommandChainFactory {

    public static @NotNull Command create() {
        Command start = new StartCommand();
        Command help = new HelpCommand();
        Command track = new TrackCommand();
        Command untrack = new UntrackCommand();
        Command list = new ListCommand();
        Command empty = new EmptyCommand();

        start.setNextCommand(help);
        help.setNextCommand(track);
        track.setNextCommand(untrack);
        untrack.setNextCommand(list);
        list.setNextCommand(empty);

        return start;
    }
}
