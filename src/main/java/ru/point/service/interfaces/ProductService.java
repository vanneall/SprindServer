package ru.point.service.interfaces;

import jakarta.annotation.Nullable;
import lombok.NonNull;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<FeedProductDto> getProducts(int offset, int limit, @Nullable String username);
    List<FeedProductDto> getProductsByName(@NonNull String name, int offset, int limit, @Nullable String username);
    ProductDto getProductById(@NonNull Long id, @Nullable String username);
    List<FeedProductDto> getProductsByCategory(int offset, int limit, Long categoryId, @Nullable String username);
    List<FeedProductDto> getProductsByShop(int offset, int limit, Long categoryId, @Nullable String username);
}
