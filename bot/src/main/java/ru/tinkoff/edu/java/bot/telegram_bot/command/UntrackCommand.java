package ru.tinkoff.edu.java.bot.telegram_bot.command;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.telegram_bot.command.enums.CommandType;

@Slf4j
public final class UntrackCommand extends Command {

    private static final @NotNull CommandType TYPE = CommandType.UNTRACK;

    private static final @NotNull String UNTRACKING_LINK_RESPONSE_TEXT = "Untracked link: %s";

    @SuppressWarnings("DuplicatedCode")
    @Override
    public SendMessage handle(@NotNull Update update) {
        if (supports(update)) {
            String messageText = update.getMessage().getText();
            String[] commandArgs = messageText.split(" ");

            if (commandArgs.length > 1) {
                SendMessage response = new SendMessage();
                String link = commandArgs[1];

                // TODO: 04.04.2023 implement untracking link after introducing database
                String responseText = String.format(UNTRACKING_LINK_RESPONSE_TEXT, link);
                log.info(responseText);

                response.setText(responseText);
                return response;
            }
            log.info("Invalid link or not a link at all provided");
        }

        return nextCommand.handle(update);
    }

    @Override
    public boolean supports(@NotNull Update update) {
        return TYPE.supports(update);
    }
}
