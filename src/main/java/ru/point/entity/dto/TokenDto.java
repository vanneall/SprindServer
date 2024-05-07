package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenDto(
    @JsonProperty(value = "token")
    String token
){}
