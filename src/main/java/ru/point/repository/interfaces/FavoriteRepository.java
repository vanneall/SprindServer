package ru.point.repository.interfaces;

import ru.point.entity.table.Product;

import java.util.List;
import java.util.Set;

public interface FavoriteRepository {
    Set<Product> getByUsername(String username);
}
