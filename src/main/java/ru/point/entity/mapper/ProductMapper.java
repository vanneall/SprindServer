package ru.point.entity.mapper;

import ru.point.entity.Product;
import ru.point.entity.dto.ProductDto;

public class ProductMapper {
    private ProductMapper() {
    }

    public static ProductDto map(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getPhotosUrl()
        );
    }

}
