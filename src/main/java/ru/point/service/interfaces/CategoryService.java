package ru.point.service.interfaces;

import lombok.NonNull;
import ru.point.entity.dto.CategoryDto;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.dto.ProductDto;
import ru.point.entity.table.complex.CategoriesInfoDto;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    List<CategoryDto> getAvailableCategories();

    CategoriesInfoDto getCategoryInfo(@NonNull Long categoryId);

    List<FeedProductDto> getProductsByCategoryId(int offset, int limit, Long categoryId, String username);
}
