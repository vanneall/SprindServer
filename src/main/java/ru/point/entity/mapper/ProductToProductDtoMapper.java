package ru.point.entity.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.point.entity.dto.ProductDto;
import ru.point.entity.table.Product;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductToProductDtoMapper implements Function<Product, ProductDto> {

    private final CharacteristicToCharacteristicDtoMapper characteristicDtoMapper;

    private final ReviewToReviewDtoMapper reviewDtoMapper;

    private final ShopToShopDtoMapper shopDtoMapper;

    private final CategoryToCategoryDtoMapper categoryDtoMapper;

    @Override
    public ProductDto apply(Product product) {
        return new ProductDto(
            product.getId(),
            product.getName(),
            product.getCount(),
            product.getPrice(),
            product.getDescription(),
            product.getCharacteristics().stream().map(characteristicDtoMapper).collect(Collectors.toSet()),
            product.getReviews().stream().limit(3).map(reviewDtoMapper).collect(Collectors.toSet()),
            product.getPhotosUrl(),
            shopDtoMapper.apply(product.getShop()),
            categoryDtoMapper.apply(product.getCategory())
        );
    }
}
