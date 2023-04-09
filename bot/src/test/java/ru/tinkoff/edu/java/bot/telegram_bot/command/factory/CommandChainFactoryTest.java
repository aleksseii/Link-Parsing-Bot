package ru.tinkoff.edu.java.bot.telegram_bot.command.factory;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.bot.client.ScrapperClientImpl;
import ru.tinkoff.edu.java.bot.telegram_bot.command.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandChainFactoryTest {

    private static final @NotNull ScrapperClientImpl DEFAULT_CLIENT = new ScrapperClientImpl();

    private static final @NotEmpty List<@NotNull Command> SOURCE_COMMANDS = List.of(
            new StartCommand(DEFAULT_CLIENT),
            new HelpCommand(),
            new TrackCommand(DEFAULT_CLIENT),
            new UntrackCommand(DEFAULT_CLIENT),
            new ListCommand(DEFAULT_CLIENT),
            new UnsupportedCommand()
    );

    @Test
    void create_shouldReturnCorrectChainOfCommands() {
        final Command commandChain = CommandChainFactory.create(SOURCE_COMMANDS);

        int i = 0;
        for (Command current = commandChain; current != null; current = current.getNextCommand()) {
            Command expected = SOURCE_COMMANDS.get(i++);
            assertEquals(expected.getClass(), current.getClass());
        }
        assertEquals(SOURCE_COMMANDS.size(), i);
    }
}
