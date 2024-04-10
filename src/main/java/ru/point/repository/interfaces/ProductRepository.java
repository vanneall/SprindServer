package ru.point.repository.interfaces;

import org.springframework.stereotype.Repository;
import ru.point.entity.Product;
import ru.point.entity.dto.ProductDto;

import java.util.List;

@Repository
public interface ProductRepository {
    List<ProductDto> getProducts();
}
