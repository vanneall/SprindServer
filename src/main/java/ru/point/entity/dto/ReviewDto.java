package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record ReviewDto(
    @JsonProperty(value = "id")
    Long id,
    @JsonProperty(value = "username")
    String username,
    @JsonProperty(value = "description")
    String description,
    @JsonProperty(value = "rating")
    Float rating
) {
}
