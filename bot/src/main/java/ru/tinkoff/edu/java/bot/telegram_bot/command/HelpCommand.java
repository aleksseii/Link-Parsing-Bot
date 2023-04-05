package ru.tinkoff.edu.java.bot.telegram_bot.command;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.telegram_bot.command.enums.CommandType;

import java.util.List;

import static ru.tinkoff.edu.java.bot.telegram_bot.command.enums.CommandType.*;

@Slf4j
public final class HelpCommand extends Command {

    private static final @NotNull CommandType TYPE = HELP;

    @Override
    public @NotNull SendMessage handle(@NotNull Update update) {
        if (supports(update)) {
            SendMessage respond = new SendMessage();
            respond.setText(buildHelpMessage());

            log.info("Showed help message");
            return respond;
        }
        return nextCommand.handle(update);
    }

    @Override
    public boolean supports(@NotNull Update update) {
        return TYPE.supports(update);
    }

    private static @NotNull String buildHelpMessage() {

        StringBuilder sb = new StringBuilder("Supported commands:\r\n");
        for (CommandType command :
                List.of(START, HELP, TRACK, UNTRACK, LIST)) {

            sb.append(
                    String.format("%s — %s", command.getCommandName(), command.getDescription())
            ).append("\r\n");
        }

        return sb.toString();
    }
}
