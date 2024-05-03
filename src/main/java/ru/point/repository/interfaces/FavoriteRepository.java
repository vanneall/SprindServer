package ru.point.repository.interfaces;

import ru.point.entity.table.Product;

import java.util.List;

public interface FavoriteRepository {

    List<Product> getByUsername(String username);

    void addFavoriteById(String username, Long id);
}
