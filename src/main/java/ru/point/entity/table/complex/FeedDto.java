package ru.point.entity.table.complex;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.point.entity.table.Address;

public record FeedDto(
    @JsonProperty(value = "address")
    Address address
) {
}
