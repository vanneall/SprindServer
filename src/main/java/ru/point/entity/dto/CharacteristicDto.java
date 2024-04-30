package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record CharacteristicDto(
        @JsonProperty(value = "name")
        String name,
        @JsonProperty("description")
        String inner
) {
}
