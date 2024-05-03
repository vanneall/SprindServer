package ru.point.repository.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Repository;
import ru.point.entity.dto.ProductDto;
import ru.point.entity.mapper.ProductToProductDtoMapper;
import ru.point.entity.table.*;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.mapper.ProductToFeedProductDtoMapper;
import ru.point.repository.interfaces.ProductRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Product> getProducts() {
        TypedQuery<Product> typedQuery = entityManager.createQuery("from Product", Product.class);

        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public List<Product> getProductsByName(String name) {
        TypedQuery<Product> typedQuery = entityManager.createQuery("from Product pr where pr.name like :name", Product.class);
        List<Product> productDtos = typedQuery
                .setParameter("name", "%" + name + "%")
                .getResultList();

        if (productDtos.isEmpty()) throw new EntityNotFoundException("Products not found");

        return productDtos;
    }

    @Override
    @Transactional
    public Product getProductById(Long id) {
        if (id <= 0) {
            throw new IllegalIdentifierException("Id must be greater then 0");
        }

        Product product = entityManager.find(Product.class, id);

        if (product == null) {
            throw new EntityNotFoundException("Product doesn't exist");
        }

        return product;
    }
}
