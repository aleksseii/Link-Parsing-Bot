package ru.tinkoff.edu.java.scrapper.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.OffsetDateTime;

public record StackOverflowQuestionResponse(@JsonProperty("question_id") @Positive long id,
                                            @JsonProperty("title") @NotBlank String title,
                                            @JsonProperty("is_answered") boolean isAnswered,
                                            @JsonProperty("answer_count") @Positive int answerCount,
                                            @JsonProperty("creation_date") @NotNull OffsetDateTime createdAt) {
}
