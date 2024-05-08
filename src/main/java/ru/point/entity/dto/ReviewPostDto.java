package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReviewPostDto(
    @JsonProperty("description")
    String description,
    @JsonProperty("rating")
    Float rating
) {
}
