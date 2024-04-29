package ru.point.entity.mapper;

import ru.point.entity.dto.ShopInProductDto;
import ru.point.entity.table.Shop;

public class ShopToShopDtoMapper {
    private ShopToShopDtoMapper() {
    }

    public static ShopInProductDto map(Shop shop) {
        return new ShopInProductDto(
                shop.getId(),
                shop.getName()
        );
    }
}
