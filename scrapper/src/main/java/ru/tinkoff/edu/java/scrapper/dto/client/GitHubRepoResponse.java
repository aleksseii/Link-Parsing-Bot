package ru.tinkoff.edu.java.scrapper.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.OffsetDateTime;

public record GitHubRepoResponse(@JsonProperty("id") @Positive long id,
                                 @JsonProperty("full_name") @NotBlank String fullName,
                                 @JsonProperty("created_at") @NotNull OffsetDateTime createdAt,
                                 @JsonProperty("updated_at") @NotNull OffsetDateTime updatedAt) {
}
