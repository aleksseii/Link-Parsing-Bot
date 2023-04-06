package ru.tinkoff.edu.java.bot.telegram_bot.command;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.dto.client.LinkResponse;
import ru.tinkoff.edu.java.bot.telegram_bot.command.enums.CommandType;

import java.net.URI;
import java.util.List;

@Slf4j
@Component
public final class ListCommand extends Command {

    private static final @NotNull CommandType TYPE = CommandType.LIST;

    private final @NotNull ScrapperClient client;

    @Autowired
    public ListCommand(@NotNull ScrapperClient client) {
        this.client = client;
    }

    @Override
    public @NotNull SendMessage handle(@NotNull Update update) {
        if (supports(update)) {
            SendMessage messageResponse = new SendMessage();

            long chatId = update.getMessage().getChatId();
            List<LinkResponse> links = client.getAllLinks(chatId).links();
            String messageText = buildListMessage(links);
            messageResponse.setText(messageText);

            return messageResponse;
        }

        return nextCommand.handle(update);
    }

    @Override
    public boolean supports(@NotNull Update update) {
        return TYPE.supports(update);
    }

    private static @NotNull String buildListMessage(@NotNull List<@NotNull LinkResponse> linkResponses) {

        if (linkResponses.isEmpty()) {
            log.info("Showed special message saying about not tracking any links");
            return "You are not tracking any links right now\r\n";
        }

        final StringBuilder sb = new StringBuilder("Tracking links:\r\n");
        for (LinkResponse response : linkResponses) {
            URI url = response.url();
            sb.append(url.toString()).append("\r\n");
        }

        log.info("Showed fulfilled list of tracking link");
        return sb.toString();
    }
}
