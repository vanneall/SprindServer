package ru.point.entity.table.complex;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.point.entity.OrderSummary;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.table.Address;

import java.util.List;

public record ComplexFeedDto(
    @JsonProperty(value = "address")
    Address address,
    @JsonProperty(value = "summaryPrice")
    OrderSummary summary
) {
}
