package ru.tinkoff.edu.java.bot.telegram_bot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public final class UpdateHandler {


    public SendMessage handle(Update update) {

        return null;
    }
}
