package ru.tinkoff.edu.java.scrapper.dto.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public record ChatLink(long chatId,
                       long linkId) {
}
