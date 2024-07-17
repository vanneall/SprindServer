package ru.point.service.implementations;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.CategoryDto;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.dto.ShopDto;
import ru.point.entity.table.Category;
import ru.point.entity.table.complex.CategoriesInfoDto;
import ru.point.repository.interfaces.CategoryRepository;
import ru.point.service.interfaces.CategoryService;
import ru.point.service.interfaces.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ProductService productService;

    @Override
    public List<CategoryDto> getAvailableCategories() {
        return categoryRepository.findAll().stream().map(category -> new CategoryDto(
            category.getId(),
            category.getName(),
            category.getPhotoUrl()
        )).collect(Collectors.toList());
    }

    @Override
    public List<FeedProductDto> getProductsByCategoryId(int offset, int limit, Long categoryId, String username) {
        return productService.getProductsByCategory(offset, limit, categoryId, username);
    }

    @Override
    public CategoriesInfoDto getCategoryInfo(@NonNull Long categoryId) {
        Category category = categoryRepository.getCategoryById(categoryId);
        return new CategoriesInfoDto(
            category.getId(),
            category.getName(),
            category.getShops().stream().map(shop -> new ShopDto(
                shop.getId(),
                shop.getName(),
                shop.getPhotoUrl()
            )).collect(Collectors.toSet())
        );
    }
}
