package ru.point.repository.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.point.entity.Product;
import ru.point.entity.dto.ProductDto;
import ru.point.entity.mapper.ProductMapper;
import ru.point.repository.interfaces.ProductRepository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<ProductDto> getProducts(Integer limit, Integer offset) {
        TypedQuery<Product> typedQuery = entityManager.createQuery("from Product", Product.class);
        if (limit != null && offset != null) {
            setLimitAndOffset(typedQuery, limit, offset);
        }

        return typedQuery
                .getResultStream()
                .map(ProductMapper::map)
                .toList();
    }

    @Override
    public List<ProductDto> getProductsByName(Integer limit, Integer offset, String name) {
        TypedQuery<Product> typedQuery = entityManager.createQuery("from Product pr where pr.name like :name", Product.class);
        if (limit != null && offset != null) {
            setLimitAndOffset(typedQuery, limit, offset);
        }

        return typedQuery
                .setParameter("name", "%" + name + "%")
                .getResultStream()
                .map(ProductMapper::map)
                .toList();
    }

    private void setLimitAndOffset(TypedQuery<?> typedQuery, int limit, int offset) {
        typedQuery.setFirstResult(offset);
        typedQuery.setMaxResults(limit);
    }
}
