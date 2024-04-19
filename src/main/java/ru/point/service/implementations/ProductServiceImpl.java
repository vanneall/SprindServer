package ru.point.service.implementations;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.dto.ProductDto;
import ru.point.entity.table.Product;
import ru.point.repository.interfaces.ProductRepository;
import ru.point.service.interfaces.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FeedProductDto> getProducts(Integer limit, Integer offset) {
        return repository.getProducts(limit, offset);
    }

    @Override
    public List<FeedProductDto> getProductsByName(Integer limit, Integer offset, String name) {
        return repository.getProductsByName(limit, offset, name);
    }

    @Override
    public ProductDto getProductById(Long id) {
        return repository.getProductById(id);
    }
}
