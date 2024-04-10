package ru.point.repository.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.point.entity.Product;
import ru.point.repository.interfaces.ProductRepository;

import java.util.List;

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
}
