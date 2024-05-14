package ru.point.service.interfaces;

import lombok.NonNull;
import ru.point.entity.dto.FeedProductDto;

import java.util.List;

public interface CartService {
    List<FeedProductDto> getProductFromUserCart(final String username);

    void addProductToCart(final Long id, final String username);

    void removeProductFromCart(final Long id, final String username);

    void makeOrder(@NonNull final String username);
}
