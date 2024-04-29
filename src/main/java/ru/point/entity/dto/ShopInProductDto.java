package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ShopInProductDto(
        @JsonProperty(value = "id")
        Long id,
        @JsonProperty(value = "name")
        String name
) {
}
