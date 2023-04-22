package ru.tinkoff.edu.java.scrapper.dto.entity;

import lombok.NoArgsConstructor;

import java.net.URI;

@NoArgsConstructor
public record Link(long linkId,
                   URI url) {
}
