package ru.point.entity.mapper;

import org.springframework.stereotype.Component;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.table.Product;

import java.util.function.BiFunction;

@Component
public class ProductToFeedProductDtoMapper implements BiFunction<Product, Boolean, FeedProductDto> {
    @Override
    public FeedProductDto apply(Product product, Boolean isInFavorite) {
        return new FeedProductDto(
            product.getId(),
            product.getName(),
            product.getPrice(),
            isInFavorite,
            product.getPhotosUrl(),
            product.getCount(),
            product.getReviews().size()
        );
    }
}
