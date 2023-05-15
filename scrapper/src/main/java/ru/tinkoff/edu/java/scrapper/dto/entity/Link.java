package ru.tinkoff.edu.java.scrapper.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.URI;

@Data
@AllArgsConstructor
public final class Link {

    private long linkId;

    private URI url;
}
