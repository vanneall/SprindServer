package ru.point.repository.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.point.entity.table.Cart;
import ru.point.entity.table.Product;
import ru.point.entity.table.User;
import ru.point.repository.interfaces.CartRepository;

import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@Repository
public class CartRepositoryImpl implements CartRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Set<Product> getAllByUsername(final String username) {
        return entityManager.find(User.class, username).getCart().getProducts();
    }

    @Transactional
    @Override
    public void addProduct(final Product product, final String username) {
        entityManager.find(User.class, username).getCart().getProducts().add(product);
    }

    @Transactional
    @Override
    public void clear(final String username) {
        entityManager.find(User.class, username).getCart().setProducts(Collections.emptySet());
    }

    @Override
    @Transactional
    public void deleteById(Long id, String username) {
        Cart cart = entityManager.find(User.class, username).getCart();
        cart.getProducts().removeIf(product -> product.getId().equals(id));
    }
}
