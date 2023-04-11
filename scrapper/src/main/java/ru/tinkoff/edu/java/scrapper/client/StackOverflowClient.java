package ru.tinkoff.edu.java.scrapper.client;

import jakarta.validation.constraints.Positive;
import ru.tinkoff.edu.java.scrapper.dto.client.StackOverflowQuestionResponse;

public sealed interface StackOverflowClient permits StackOverflowClientImpl {

    StackOverflowQuestionResponse getQuestion(@Positive long questionId);
}
