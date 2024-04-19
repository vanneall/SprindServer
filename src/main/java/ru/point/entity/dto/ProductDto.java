package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import ru.point.entity.table.Category;
import ru.point.entity.table.Price;
import ru.point.entity.table.Shop;

import java.util.List;
import java.util.Map;


public record ProductDto(
        @NonNull
        @JsonProperty(value = "id")
        Long id,
        @NonNull
        @JsonProperty(value = "name")
        String name,
        @NonNull
        @JsonProperty(value = "price")
        Price price,
        @JsonProperty(value = "description")
        String description,
        @NonNull
        @JsonProperty(value = "characteristics")
        Map<String, String> characteristics,
        @NonNull
        @JsonProperty(value = "photosUrl")
        List<String> photosUrl,
        @NonNull
        @JsonProperty(value = "shop")
        ShopDto shop,
        @NonNull
        @JsonProperty(value = "category")
        CategoryDto category
) {
}
