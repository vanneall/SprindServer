package ru.point.repository.interfaces;

import jakarta.persistence.SequenceGenerator;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.dto.ProductDto;
import ru.point.entity.table.Product;

import java.util.List;
import java.util.Set;

public interface ProductRepository {
    List<Product> getProducts(int offset, int limit);
    List<Product> getProductsByName(String name, int offset, int limit);
    Product getProductById(Long id);
    List<Product> getProductByCategoryId(int offset, int limit, Long categoryId);
    List<Product> getProductsFromShopById(@NonNull Long id, int offset, int limit);
}
