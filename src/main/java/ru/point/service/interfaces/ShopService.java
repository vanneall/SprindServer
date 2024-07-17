package ru.point.service.interfaces;

import ru.point.entity.dto.FeedProductDto;

import java.util.List;

public interface ShopService {
    public List<FeedProductDto> getProductsByShop(int offset, int limit, long shopId, String username);
}
