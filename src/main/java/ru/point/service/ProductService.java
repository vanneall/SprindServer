package ru.point.service;

import org.springframework.stereotype.Service;
import ru.point.entity.Product;
import ru.point.entity.dto.ProductDto;
import ru.point.repository.interfaces.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductDto> getProducts(Integer limit, Integer offset) {
        return repository.getProducts(limit, offset);
    }
}
