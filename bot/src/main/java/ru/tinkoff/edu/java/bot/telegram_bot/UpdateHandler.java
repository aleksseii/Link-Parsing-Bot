package ru.tinkoff.edu.java.bot.telegram_bot;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.telegram_bot.command.Command;
import ru.tinkoff.edu.java.bot.telegram_bot.command.factory.CommandChainFactory;

@Service
public final class UpdateHandler {

    private final @NotNull Command commandChain;

    public UpdateHandler() {
        this.commandChain = CommandChainFactory.create();
    }

    public SendMessage handle(Update update) {

        return commandChain.handle(update);
    }
}
