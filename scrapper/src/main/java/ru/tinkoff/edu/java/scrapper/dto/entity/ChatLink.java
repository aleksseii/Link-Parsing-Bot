package ru.tinkoff.edu.java.scrapper.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class ChatLink{

    private long chatId;

    private long linkId;
}
