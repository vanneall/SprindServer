package ru.point.service.interfaces;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.dto.ProductDto;

import java.security.Principal;
import java.util.List;

public interface ProductService {

    List<FeedProductDto> getProducts(String username);

    List<FeedProductDto> getProductsByName(String username, @NonNull String name);

    ProductDto getProductById(@NonNull Long id, String username);
}
