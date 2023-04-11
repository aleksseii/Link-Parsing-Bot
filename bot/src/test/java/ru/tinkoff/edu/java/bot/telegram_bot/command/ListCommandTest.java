package ru.tinkoff.edu.java.bot.telegram_bot.command;

import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.dto.client.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.client.ListLinksResponse;
import ru.tinkoff.edu.java.bot.telegram_bot.command.enums.CommandType;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListCommandTest {

    private static final long EXPECTED_CHAT_ID = 1L;
    private static final @NotNull String VALID_MESSAGE_TEXT = CommandType.LIST.getCommandName();

    private static final @NotNull String NO_TRACKING_LINKS_RESPONSE_TEXT =
            "You are not tracking any links right now\r\n";
    private static final @NotNull String TRACKING_LINKS_PREFIX = "Tracking links:\r\n";

    @InjectMocks
    private @NotNull ListCommand listCommand;

    @Mock
    private @NotNull ScrapperClient client;

    @ParameterizedTest
    @ValueSource(ints = { 1, 5, 10, 100 , 500 })
    void handle_shouldReturnHandledSendMessage_whenValidMessageTextAndThereAreLinks(int size) {
        Update update = getUpdateValidCommand();
        ListLinksResponse linksResponses = getListLinksResponse(size);
        when(client.getAllLinks(EXPECTED_CHAT_ID)).thenReturn(linksResponses);

        SendMessage actual = listCommand.handle(update);
        String actualText = actual.getText();

        assertAll(
                "Correct formatting",
                () -> assertTrue(actualText.startsWith(TRACKING_LINKS_PREFIX)),
                () -> assertEquals(actualText.chars().filter(ch -> ch == '\r').count(), size + 1),
                () -> assertEquals(actualText.chars().filter(ch -> ch == '\n').count(), size + 1),
                () -> assertEquals(actualText.chars().filter(ch -> ch == '\t').count(), size)
        );
    }

    @Test
    void handle_shouldReturnHandledSendMessage_whenValidMessageTextAndNoLinks() {
        Update update = getUpdateValidCommand();
        ListLinksResponse emptyListLinksResponse = getListLinksResponse(0);
        when(client.getAllLinks(EXPECTED_CHAT_ID)).thenReturn(emptyListLinksResponse);

        SendMessage actual = listCommand.handle(update);
        String actualText = actual.getText();

        assertEquals(actualText, NO_TRACKING_LINKS_RESPONSE_TEXT);
    }

    private @NotNull Update getUpdateValidCommand() {
        Update update = new Update();

        Message tgMessage = new Message();
        Chat tgChat = new Chat(EXPECTED_CHAT_ID, "private");
        tgMessage.setChat(tgChat);
        tgMessage.setText(VALID_MESSAGE_TEXT);

        update.setMessage(tgMessage);
        return update;
    }

    private @NotNull ListLinksResponse getListLinksResponse(int size) {
        final String linkPrefix = "https://link-";

        List<LinkResponse> linkResponses = new ArrayList<>(size);
        for (int i = 1; i <= size; i++) {
            linkResponses.add(new LinkResponse(i, URI.create(linkPrefix + i)));
        }
        return new ListLinksResponse(linkResponses, size);
    }
}
