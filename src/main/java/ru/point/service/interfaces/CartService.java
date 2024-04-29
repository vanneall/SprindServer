package ru.point.service.interfaces;

import ru.point.entity.dto.FeedProductDto;

import java.util.List;

public interface CartService {
    List<FeedProductDto> getProductFromUserCart(final String username);

    void addProductToCart(final Long id, final String username);

    void clearCart(final String username);
}
