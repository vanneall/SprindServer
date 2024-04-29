package ru.point.repository.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.mapper.ProductToFeedProductDtoMapper;
import ru.point.entity.table.Cart;
import ru.point.entity.table.Product;
import ru.point.repository.interfaces.CartRepository;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Repository
public class CartRepositoryImpl implements CartRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<FeedProductDto> getAllByUsername(final String username) {
        TypedQuery<Product> typedQuery = entityManager.createQuery(
                "select cart.products from Cart cart where cart.user.username = :username",
                Product.class
        );

        typedQuery.setParameter("username", username);
        return typedQuery.getResultList().stream().map(ProductToFeedProductDtoMapper::map).toList();
    }

    @Transactional
    @Override
    public void addProduct(final Product product, final String username) {
        var cart = getCartByUsername(username);
        cart.getProducts().add(product);
    }

    @Transactional
    @Override
    public void clear(final String username) {
        var cart = getCartByUsername(username);
        cart.setProducts(Collections.emptySet());
    }

    private Cart getCartByUsername(final String username) {
        return entityManager.createQuery(
                "from Cart cart where cart.user.username = :username",
                Cart.class
        ).setParameter("username", username).getSingleResult();
    }
}
