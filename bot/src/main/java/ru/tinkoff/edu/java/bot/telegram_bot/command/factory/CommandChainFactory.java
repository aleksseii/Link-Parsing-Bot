package ru.tinkoff.edu.java.bot.telegram_bot.command.factory;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ru.tinkoff.edu.java.bot.telegram_bot.command.Command;

import java.util.Iterator;
import java.util.List;

public final class CommandChainFactory {

    public static @NotNull Command create(@NotEmpty List<@NotNull Command> commands) {
        final Iterator<@NotNull Command> iterator = commands.iterator();
        final Command head = iterator.next();

        Command current = head;
        while (iterator.hasNext()) {
            Command next = iterator.next();
            current.setNextCommand(next);
            current = next;
        }
        return head;
    }
}
