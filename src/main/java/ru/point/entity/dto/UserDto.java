package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.point.entity.table.Address;

public record UserDto(
    @JsonProperty(value = "username")
    String telephone,
    @JsonProperty(value = "name")
    String name,
    @JsonProperty(value = "secondName")
    String secondName,
    @JsonProperty(value = "email")
    String email,
    @JsonProperty(value = "address")
    Address address
) {
}
