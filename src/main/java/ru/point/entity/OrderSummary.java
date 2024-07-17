package ru.point.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.point.entity.table.Currency;

public record OrderSummary(
    @JsonProperty("delivery")
    int delivery,
    @JsonProperty("products")
    int products,
    @JsonProperty("discount")
    int discount,
    @JsonProperty("promocode")
    int promocode,
    @JsonProperty("summary")
    int summary
) {
}
