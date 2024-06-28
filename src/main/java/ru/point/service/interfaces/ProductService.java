package ru.point.service.interfaces;

import jakarta.annotation.Nullable;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.dto.ProductDto;

import java.security.Principal;
import java.util.List;

public interface ProductService {
    List<FeedProductDto> getProducts(int offset, int limit, @Nullable String username);

    List<FeedProductDto> getProductsByName(@NonNull String name, int offset, int limit, @Nullable String username);

    ProductDto getProductById(@NonNull Long id, @Nullable String username);
}
