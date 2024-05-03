package ru.point.entity.mapper;

import ru.point.entity.table.Product;
import ru.point.entity.dto.FeedProductDto;

public class ProductToFeedProductDtoMapper {
    private ProductToFeedProductDtoMapper() {
    }

    public static FeedProductDto map(Product product, Boolean isFavorite) {
        return new FeedProductDto(
            product.getId(),
            product.getName(),
            product.getPrice(),
            isFavorite,
            product.getPhotosUrl()
        );
    }
}
