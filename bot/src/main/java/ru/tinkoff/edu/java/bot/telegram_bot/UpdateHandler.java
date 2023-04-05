package ru.tinkoff.edu.java.bot.telegram_bot;


import jakarta.validation.constraints.NotNull;
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

    public @NotNull SendMessage handle(@NotNull Update update) {
        SendMessage response = commandChain.handle(update);
        response.setChatId(update.getMessage().getChatId());

        return response;
    }
}
