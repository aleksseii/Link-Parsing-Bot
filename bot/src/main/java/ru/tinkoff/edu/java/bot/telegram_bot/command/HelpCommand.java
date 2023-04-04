package ru.tinkoff.edu.java.bot.telegram_bot.command;

import jakarta.validation.constraints.NotNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.telegram_bot.command.enums.CommandType;

public final class HelpCommand extends Command {

    private static final @NotNull CommandType TYPE = CommandType.HELP;

    @Override
    public SendMessage handle(@NotNull Update update) {
        if (!supports(update)) {
            return nextCommand.handle(update);
        }
        long chatId = update.getMessage().getChatId();

        SendMessage respond = new SendMessage();
        respond.setChatId(chatId);
        respond.setText("");

        // TODO: 04.04.2023 implement sending help message
        return nextCommand.handle(update);
    }

    @Override
    public boolean supports(@NotNull Update update) {
        return TYPE.supports(update);
    }
}
