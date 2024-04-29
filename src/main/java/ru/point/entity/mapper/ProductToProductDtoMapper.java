package ru.point.entity.mapper;

import ru.point.entity.dto.ProductDto;
import ru.point.entity.table.Product;

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
                product.getCharacteristics(),
                product.getPhotosUrl(),
                ShopToShopDtoMapper.map(product.getShop()),
                CategoryToCategoryDtoMapper.map(product.getCategory())
        );
    }
}
