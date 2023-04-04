package ru.tinkoff.edu.java.bot.telegram_bot.command;

import jakarta.validation.constraints.NotNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.telegram_bot.command.enums.CommandType;

public final class TrackCommand extends Command {

    private static final @NotNull CommandType TYPE = CommandType.TRACK;

    @Override
    public SendMessage handle(@NotNull Update update) {
        if (!supports(update)) {
            return nextCommand.handle(update);
        }
        Message message = update.getMessage();
        long chatId = message.getChatId();

        // TODO: 04.04.2023 implement tracking after introducing database
        return nextCommand.handle(update);
    }

    @Override
    public boolean supports(@NotNull Update update) {
        return TYPE.supports(update);
    }
}
