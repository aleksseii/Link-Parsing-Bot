package ru.tinkoff.edu.java.bot.telegram_bot.command;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.dto.client.AddLinkRequest;
import ru.tinkoff.edu.java.bot.dto.client.LinkResponse;
import ru.tinkoff.edu.java.bot.telegram_bot.command.enums.CommandType;

import java.net.URI;

@Slf4j
@Component
public final class TrackCommand extends Command {

    private static final @NotNull CommandType TYPE = CommandType.TRACK;

    private static final @NotNull String TRACKING_LINK_RESPONSE_TEXT = "Started tracking link: %s";
    private static final @NotNull String SPACE = " ";

    private final @NotNull ScrapperClient client;

    @Autowired
    public TrackCommand(ScrapperClient client) {
        this.client = client;
    }

    @Override
    public @NotNull SendMessage handle(@NotNull Update update) {
        if (supports(update)) {
            Message message = update.getMessage();
            String[] commandArgs = message.getText().split(SPACE);

            if (commandArgs.length > 1) {
                SendMessage response = new SendMessage();
                String link = commandArgs[1];

                // TODO: 04.04.2023 introduce parser somewhere here to check if this link is valid

                long chatId = update.getMessage().getChatId();
                AddLinkRequest addLinkRequest = new AddLinkRequest(URI.create(link));

                LinkResponse linkResponse = client.addLink(chatId, addLinkRequest);
                String url = linkResponse.url().toString();
                log.info(String.format("User with chat id = %d started tracking link: %s", linkResponse.id(), url));

                response.setText(String.format(TRACKING_LINK_RESPONSE_TEXT, url));
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
