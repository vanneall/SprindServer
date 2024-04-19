package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ShopDto(
        @JsonProperty(value = "id")
        Long id,
        @JsonProperty(value = "name")
        String name,
        @JsonProperty(value = "description")
        String description
) {
}
