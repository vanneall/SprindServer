package ru.point.repository.interfaces;

import org.springframework.stereotype.Repository;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.dto.ProductDto;
import ru.point.entity.table.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getProducts();
    List<Product> getProductsByName(String name);
    Product getProductById(Long id);
}
