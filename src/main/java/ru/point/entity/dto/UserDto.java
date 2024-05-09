package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDto(
    @JsonProperty(value = "username", required = true)
    String telephone,
    @JsonProperty(value = "password", required = true)
    String password,
    @JsonProperty(value = "name", required = true)
    String name,
    @JsonProperty(value = "secondName", required = true)
    String secondName,
    @JsonProperty(value = "secret", required = true)
    String secret,
    @JsonProperty(value = "email", required = true)
    String email
) {
}
