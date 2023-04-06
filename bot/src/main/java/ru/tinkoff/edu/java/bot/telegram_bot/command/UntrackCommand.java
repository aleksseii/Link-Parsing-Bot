package ru.tinkoff.edu.java.bot.telegram_bot.command;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.dto.client.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.telegram_bot.command.enums.CommandType;

import java.net.URI;

@Slf4j
@Component
public final class UntrackCommand extends Command {

    private static final @NotNull CommandType TYPE = CommandType.UNTRACK;

    private static final @NotNull String UNTRACKING_LINK_RESPONSE_TEXT = "Untracked link: %s";
    private static final @NotNull String SPACE = " ";

    private final @NotNull ScrapperClient client;

    @Autowired
    public UntrackCommand(@NotNull ScrapperClient client) {
        this.client = client;
    }

    @Override
    public @NotNull SendMessage handle(@NotNull Update update) {
        if (supports(update)) {
            String messageText = update.getMessage().getText();
            String[] commandArgs = messageText.split(SPACE);

            if (commandArgs.length > 1) {
                SendMessage response = new SendMessage();
                String link = commandArgs[1];

                // TODO: 04.04.2023 introduce parser somewhere here to check if this link is valid

                long chatId = update.getMessage().getChatId();
                RemoveLinkRequest addLinkRequest = new RemoveLinkRequest(URI.create(link));

                client.removeLink(chatId, addLinkRequest);
                log.info(String.format("User with chat id = %d does not track link: %s anymore", chatId, link));

                response.setText(String.format(UNTRACKING_LINK_RESPONSE_TEXT, link));
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
