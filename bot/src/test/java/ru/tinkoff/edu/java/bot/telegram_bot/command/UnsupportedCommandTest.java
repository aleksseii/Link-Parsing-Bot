package ru.tinkoff.edu.java.bot.telegram_bot.command;

import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.client.ScrapperClientImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnsupportedCommandTest {

    private static final long EXPECTED_CHAT_ID = 1L;

    private static final @NotNull String INVALID_MESSAGE_TEXT = "/unsupported-command";
    private static final @NotNull String UNSUPPORTED_COMMAND_RESPONSE_TEXT =
            "This command is not supported.\r\nType `/help` for more info";

    private @NotNull Command commandChain;

    @BeforeEach
    void setCommandChain() {
        final ScrapperClientImpl defaultClient = new ScrapperClientImpl();

        Command start = new StartCommand(defaultClient);
        Command help = new HelpCommand();
        Command track = new TrackCommand(defaultClient);
        Command untrack = new UntrackCommand(defaultClient);
        Command list = new ListCommand(defaultClient);
        Command unsupported = new UnsupportedCommand();

        start.setNextCommand(help);
        help.setNextCommand(track);
        track.setNextCommand(untrack);
        untrack.setNextCommand(list);
        list.setNextCommand(unsupported);

        this.commandChain = start;
    }

    @Test
    void handle_shouldReturnHandledSendMessage_whenUnsupportedCommandProvided() {
        Update update = getUpdateInvalidCommand();

        SendMessage actual = commandChain.handle(update);
        String actualText = actual.getText();

        assertEquals(actualText, UNSUPPORTED_COMMAND_RESPONSE_TEXT);
    }

    private @NotNull Update getUpdateInvalidCommand() {
        Update update = new Update();

        Message tgMessage = new Message();
        Chat tgChat = new Chat(EXPECTED_CHAT_ID, "private");
        tgMessage.setChat(tgChat);
        tgMessage.setText(INVALID_MESSAGE_TEXT);

        update.setMessage(tgMessage);
        return update;
    }
}
