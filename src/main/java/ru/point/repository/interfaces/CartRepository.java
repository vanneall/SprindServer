package ru.point.repository.interfaces;

import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.table.Product;

import java.util.List;

public interface CartRepository {
    List<Product> getAllByUsername(final String username);

    void addProduct(final Product product, final String username);

    void clear(final String username);

}
