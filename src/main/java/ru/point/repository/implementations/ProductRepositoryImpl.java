package ru.point.repository.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Repository;
import ru.point.entity.dto.ProductDto;
import ru.point.entity.mapper.ProductToProductDtoMapper;
import ru.point.entity.table.*;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.mapper.ProductToFeedProductDtoMapper;
import ru.point.repository.interfaces.ProductRepository;
import ru.point.repository.utils.RepositoryUtils;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Product> getProducts(int offset, int limit) {
        TypedQuery<Product> typedQuery = entityManager.createQuery("from Product pr where pr.count > 0", Product.class);
        typedQuery = RepositoryUtils.setPagingToQuery(typedQuery, offset, limit);
        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public List<Product> getProductsByName(String name, int offset, int limit) {
        TypedQuery<Product> typedQuery = entityManager.createQuery("from Product pr where pr.name like :name and pr.count > 0", Product.class);
        typedQuery = RepositoryUtils.setPagingToQuery(typedQuery, offset, limit);
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

    @Override
    public List<Product> getProductByCategoryId(int offset, int limit, Long categoryId) {
        TypedQuery<Product> typedQuery = entityManager.createQuery(
            "from Product pr where pr.category.id = :id",
            Product.class
        );
        typedQuery = RepositoryUtils.setPagingToQuery(typedQuery, offset, limit);

        return typedQuery.setParameter("id", categoryId).getResultList();
    }

    @Override
    public List<Product> getProductsFromShopById(@NonNull Long id, int offset, int limit) {
        TypedQuery<Product> typedQuery = entityManager.createQuery(
            "from Product pr where pr.shop.id = :id",
            Product.class
        );
        typedQuery = RepositoryUtils.setPagingToQuery(typedQuery, offset, limit);

        return typedQuery.setParameter("id", id).getResultList();
    }

}
