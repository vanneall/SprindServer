package ru.point.entity.mapper;

import ru.point.entity.dto.CategoryDto;
import ru.point.entity.table.Category;

public class CategoryToCategoryDtoMapper {

    private CategoryToCategoryDtoMapper() {
    }

    public static CategoryDto map(Category category) {
        if (category == null) return null;

        return new CategoryDto(
                category.getId(),
                category.getName(),
                map(category.getCategory())
        );
    }
}
