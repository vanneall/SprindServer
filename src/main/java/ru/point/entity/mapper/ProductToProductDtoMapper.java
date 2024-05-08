package ru.point.entity.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.point.entity.dto.CharacteristicDto;
import ru.point.entity.dto.ProductDto;
import ru.point.entity.table.Characteristic;
import ru.point.entity.table.Product;

import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class ProductToProductDtoMapper {
    private ProductToProductDtoMapper() {
    }

    public static ProductDto map(Product product) {
        return new ProductDto(
            product.getId(),
            product.getName(),
            product.getCount(),
            product.getPrice(),
            product.getDescription(),
            product.getCharacteristics().stream().map(ProductToProductDtoMapper::apply).collect(Collectors.toSet()),
            product.getReviews().stream().limit(3).map(ReviewToReviewDtoMapper::map).collect(Collectors.toSet()),
            product.getPhotosUrl(),
            ShopToShopDtoMapper.map(product.getShop()),
            CategoryToCategoryDtoMapper.map(product.getCategory())
        );
    }

    private static CharacteristicDto apply(Characteristic characteristic) {
        return new CharacteristicDto(
            characteristic.getName(),
            characteristic.getDescriptions()
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue() + ";")
                .reduce((s, s2) -> s + s2).get()
        );
    }
}
