package ru.tinkoff.edu.java.bot.telegram_bot.command;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.telegram_bot.command.enums.CommandType;

@Slf4j
public final class ListCommand extends Command {

    private static final @NotNull CommandType TYPE = CommandType.LIST;

    @Override
    public @NotNull SendMessage handle(@NotNull Update update) {
        if (supports(update)) {
            SendMessage response = new SendMessage();
            response.setText(buildListMessage());

            // TODO: 04.04.2023 implement listing all the tracking links after introducing database
            log.info("Showed list of tracking links");
            return response;
        }

        return nextCommand.handle(update);
    }

    @Override
    public boolean supports(@NotNull Update update) {
        return TYPE.supports(update);
    }

    private static @NotNull String buildListMessage() {
        return "You are not tracking any links right now\r\n";
    }
}
