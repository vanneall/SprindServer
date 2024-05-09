package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

public record CreatedReviewDto(
    @JsonProperty("advantage")
    String advantage,
    @JsonProperty("disadvantage")
    String disadvantage,
    @JsonProperty("description")
    String description,
    @NonNull
    @JsonProperty("rating")
    Float rating
) {
}
