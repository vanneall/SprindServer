package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResetUserDto(
    @JsonProperty(value = "username")
    String username,
    @JsonProperty(value = "secret")
    String secret,
    @JsonProperty(value = "newPassword")
    String newPassword
) {
}
