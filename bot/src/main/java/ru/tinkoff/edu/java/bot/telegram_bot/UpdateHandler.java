package ru.tinkoff.edu.java.bot.telegram_bot;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.telegram_bot.command.Command;
import ru.tinkoff.edu.java.bot.telegram_bot.command.factory.CommandChainFactory;

import java.util.List;

@Service
public final class UpdateHandler {

    private final @NotNull Command commandChain;

    @Autowired
    public UpdateHandler(@NotEmpty List<@NotNull Command> commands) {
        this.commandChain = CommandChainFactory.create(commands);
    }

    public @NotNull SendMessage handle(@NotNull Update update) {
        SendMessage response = commandChain.handle(update);
        response.setChatId(update.getMessage().getChatId());

        return response;
    }
}
