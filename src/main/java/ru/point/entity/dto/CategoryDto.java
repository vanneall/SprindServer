package ru.point.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.point.entity.table.Category;

public record CategoryDto(
        @JsonProperty(value = "id")
        Long id,
        @JsonProperty(value = "name")
        String name,
        @JsonProperty(value = "parentCategory")
        CategoryDto parentCategoryDto
) {
}
