package ru.point.repository;

import org.springframework.stereotype.Repository;
import ru.point.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository {
    List<Product> getProducts();
}
