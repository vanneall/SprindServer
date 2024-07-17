package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CategoryDto(
    @JsonProperty(value = "id")
    Long id,
    @JsonProperty(value = "name")
    String name,
    @JsonProperty(value = "photo_url")
    String photoUrl
) {
}
