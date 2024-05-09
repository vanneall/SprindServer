package ru.point.entity.mapper;

import org.springframework.stereotype.Component;
import ru.point.entity.dto.ShopInProductDto;
import ru.point.entity.table.Shop;

import java.util.function.Function;

@Component
public class ShopToShopDtoMapper implements Function<Shop, ShopInProductDto> {
    @Override
    public ShopInProductDto apply(Shop shop) {
        return new ShopInProductDto(
            shop.getId(),
            shop.getName()
        );
    }
}
