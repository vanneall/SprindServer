package ru.point.entity.table.complex;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.point.entity.dto.ShopDto;

import java.util.Set;

public record CategoriesInfoDto(
    @JsonProperty(value = "id")
    Long id,
    @JsonProperty(value = "name")
    String name,
    @JsonProperty(value = "shops")
    Set<ShopDto> shops
) {
}
