package ru.point.entity.mapper;

import ru.point.entity.dto.CategoryInProductDto;
import ru.point.entity.table.Category;

public class CategoryToCategoryDtoMapper {

    private CategoryToCategoryDtoMapper() {
    }

    public static CategoryInProductDto map(Category category) {
        if (category == null) return null;

        return new CategoryInProductDto(
                category.getId(),
                category.getName()
        );
    }
}
