package ru.tinkoff.edu.java.bot.telegram_bot.command;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.telegram_bot.command.enums.CommandType;

@Slf4j
public final class TrackCommand extends Command {

    private static final @NotNull CommandType TYPE = CommandType.TRACK;

    private static final @NotNull String TRACKING_LINK_RESPONSE_TEXT = "Started tracking link: %s";
    private static final @NotNull String SPACE = " ";

    @SuppressWarnings("DuplicatedCode")
    @Override
    public @NotNull SendMessage handle(@NotNull Update update) {
        if (supports(update)) {
            Message message = update.getMessage();
            String[] commandArgs = message.getText().split(SPACE);

            if (commandArgs.length > 1) {
                SendMessage response = new SendMessage();
                String link = commandArgs[1];

                // TODO: 04.04.2023 implement tracking link after introducing database
                String responseText = String.format(TRACKING_LINK_RESPONSE_TEXT, link);
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
