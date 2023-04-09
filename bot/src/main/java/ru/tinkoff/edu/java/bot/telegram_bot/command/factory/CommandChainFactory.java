package ru.tinkoff.edu.java.bot.telegram_bot.command.factory;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.ConfigurableApplicationContext;
import ru.tinkoff.edu.java.bot.telegram_bot.command.*;

public final class CommandChainFactory {

    public static @NotNull Command create(@NotNull ConfigurableApplicationContext context) {

        Command start = context.getBean("startCommand", Command.class);
        Command help = context.getBean("helpCommand", Command.class);
        Command track = context.getBean("trackCommand", Command.class);
        Command untrack = context.getBean("untrackCommand", Command.class);
        Command list = context.getBean("listCommand", Command.class);
        Command unsupported = context.getBean("unsupportedCommand", Command.class);

        start.setNextCommand(help);
        help.setNextCommand(track);
        track.setNextCommand(untrack);
        untrack.setNextCommand(list);
        list.setNextCommand(unsupported);

        return start;
    }
}
