package ru.point.service.implementations;

import lombok.AllArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.dto.ProductDto;
import ru.point.entity.table.Product;
import ru.point.repository.interfaces.ProductRepository;
import ru.point.service.interfaces.ProductService;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

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
