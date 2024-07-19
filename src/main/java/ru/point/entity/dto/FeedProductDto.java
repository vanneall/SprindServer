package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.point.entity.table.Price;

import java.util.List;

public record FeedProductDto(
        @JsonProperty(value = "id")
        Long id,
        @JsonProperty(value = "name")
        String name,
        @JsonProperty(value = "price")
        Price price,
        @JsonProperty(value = "isFavorite")
        Boolean isFavorite,
        @JsonProperty(value = "isInCart")
        Boolean isInCart,
        @JsonProperty(value = "photosUrl")
        List<String> photosUrl,
        @JsonProperty(value = "count")
        Integer count,
        @JsonProperty(value = "rating")
        Float rating,
        @JsonProperty(value = "reviewCount")
        Integer reviewCount
) {
}


