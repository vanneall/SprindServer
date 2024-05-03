package ru.point.repository.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.point.entity.table.Product;
import ru.point.repository.interfaces.FavoriteRepository;

import java.util.List;

@Repository
public class FavoriteRepositoryImpl implements FavoriteRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Product> getByUsername(String username) {
        TypedQuery<Product> typedQuery = entityManager.createQuery(
                "select favorites from User user where user.username = :username",
                Product.class
        );

        return typedQuery.setParameter("username", username).getResultList();
    }

    @Override
    public void addFavoriteById(String username, Long id) {

    }
}
