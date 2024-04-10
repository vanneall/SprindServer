package ru.point.repository.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.point.entity.Shop;
import ru.point.repository.interfaces.ShopRepository;

import java.util.List;

@Repository
public class ShopRepositoryImpl implements ShopRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Shop> getAllShop() {
        return entityManager.createQuery("from Shop", Shop.class).getResultList();
    }
}
