package ru.point.entity.mapper;

import ru.point.entity.dto.ShopDto;
import ru.point.entity.table.Shop;

public class ShopToShopDtoMapper {
    private ShopToShopDtoMapper() {
    }

    public static ShopDto map(Shop shop) {
        return new ShopDto(
                shop.getId(),
                shop.getName(),
                shop.getDescription()
        );
    }
}
