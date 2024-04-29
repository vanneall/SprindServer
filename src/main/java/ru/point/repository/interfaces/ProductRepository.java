package ru.point.repository.interfaces;

import org.springframework.stereotype.Repository;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.dto.ProductDto;
import ru.point.entity.table.Product;

import java.util.List;

public interface ProductRepository {
    List<FeedProductDto> getProducts(Integer limit, Integer offset);

    List<FeedProductDto> getProductsByName(Integer limit, Integer offset, String name);

    Product getProductById(Long id);
}
