package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderDto(
    @JsonProperty(value = "id")
    Long id,

    @JsonProperty(value = "deliveryCost")
    Double deliveryCost,

    @JsonProperty(value = "productsCost")
    Double productsCost,

    @JsonProperty(value = "summaryCost")
    Double summaryCost
){}
