package ru.point.entity.mapper;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ru.point.entity.dto.CategoryInProductDto;
import ru.point.entity.table.Category;

import java.util.function.Function;

@Component
public class CategoryToCategoryDtoMapper implements Function<Category, CategoryInProductDto> {
    @Override
    public CategoryInProductDto apply(@NonNull Category category) {
        return new CategoryInProductDto(
            category.getId(),
            category.getName()
        );
    }
}
