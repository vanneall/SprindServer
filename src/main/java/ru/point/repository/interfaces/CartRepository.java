package ru.point.repository.interfaces;

import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.table.Product;

import java.util.List;
import java.util.Set;

public interface CartRepository {
    Set<Product> getAllByUsername(final String username);

    void addProduct(final Product product, final String username);

    void clear(final String username);

}
