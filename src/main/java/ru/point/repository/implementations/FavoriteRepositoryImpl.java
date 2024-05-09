package ru.point.repository.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.point.entity.table.Product;
import ru.point.entity.table.User;
import ru.point.repository.interfaces.FavoriteRepository;

import java.util.List;
import java.util.Set;

@Repository
public class FavoriteRepositoryImpl implements FavoriteRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Set<Product> getByUsername(String username) {
        return entityManager.find(User.class, username).getFavorites();
    }
}
