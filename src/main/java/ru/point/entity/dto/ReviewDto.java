package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReviewDto(
    @JsonProperty(value = "id")
    Long id,
    @JsonProperty(value = "username")
    String username,
    @JsonProperty("advantage")
    String advantage,
    @JsonProperty("disadvantage")
    String disadvantage,
    @JsonProperty(value = "description")
    String description,
    @JsonProperty(value = "rating")
    Float rating
) {
}
