package ru.point.service;

import org.springframework.stereotype.Service;
import ru.point.entity.Product;
import ru.point.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    ProductRepository repository;

    ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getProducts() {
        return repository.getProducts();
    }
}
