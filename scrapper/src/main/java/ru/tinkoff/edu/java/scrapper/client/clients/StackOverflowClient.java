package ru.tinkoff.edu.java.scrapper.client.clients;

import jakarta.validation.constraints.Positive;
import ru.tinkoff.edu.java.scrapper.client.dto.StackOverflowQuestionResponse;

public sealed interface StackOverflowClient permits StackOverflowClientImpl {

    StackOverflowQuestionResponse getQuestion(@Positive long questionId);
}
