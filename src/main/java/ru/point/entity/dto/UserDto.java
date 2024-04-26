package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDto(
        @JsonProperty(value = "username", required = true)
        String username,
        @JsonProperty(value = "password", required = true)
        String password
) {
}
