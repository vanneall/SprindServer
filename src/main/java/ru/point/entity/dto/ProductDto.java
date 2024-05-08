package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import ru.point.entity.table.Characteristic;
import ru.point.entity.table.Price;

import java.util.List;
import java.util.Set;


public record ProductDto(
        @NonNull
        @JsonProperty(value = "id")
        Long id,
        @NonNull
        @JsonProperty(value = "name")
        String name,
        @NonNull
        @JsonProperty(value = "count")
        Integer count,
        @NonNull
        @JsonProperty(value = "price")
        Price price,
        @JsonProperty(value = "description")
        String description,
        @NonNull
        @JsonProperty(value = "characteristics")
        Set<CharacteristicDto> characteristics,
        @NonNull
        @JsonProperty(value = "reviews")
        Set<ReviewDto> review,
        @NonNull
        @JsonProperty(value = "photosUrl")
        List<String> photosUrl,
        @NonNull
        @JsonProperty(value = "shop")
        ShopInProductDto shop,
        @NonNull
        @JsonProperty(value = "category")
        CategoryInProductDto category
) {
}
