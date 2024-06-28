package ru.point.service.implementations;

import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.mapper.ProductToFeedProductDtoMapper;
import ru.point.entity.table.Cart;
import ru.point.entity.table.Order;
import ru.point.entity.table.Product;
import ru.point.entity.table.User;
import ru.point.repository.interfaces.CartRepository;
import ru.point.repository.interfaces.ProductRepository;
import ru.point.service.interfaces.CartService;
import ru.point.service.interfaces.horizontal.UserServiceHorizontal;
import ru.point.utils.factory.interfaces.OrderFactory;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final OrderFactory orderFactory;
    private final ProductToFeedProductDtoMapper productDtoMapper;
    private final UserServiceHorizontal userServiceHorizontal;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public List<FeedProductDto> getProductFromUserCart(String username) {
        User user = userServiceHorizontal.getUserByUsername(username);
        var favorites = user.getFavorites();

        return user.getCart().getProducts()
            .stream()
            .map(product -> productDtoMapper.apply(
                    product,
                    favorites.contains(product),
                    true
                )
            )
            .toList();
    }

    @Override
    public void addProductToCart(Long id, String username) {
        Product product = productRepository.getProductById(id);
        if (product == null) throw new EntityNotFoundException("Product with this id doesn't exist");

        cartRepository.addProduct(product, username);
    }

    @Override
    public void removeProductFromCart(@Nullable Long productId, String username) {
        if (productId == null) {
            cartRepository.clear(username);
        } else {
            cartRepository.deleteById(productId, username);
        }

    }

    @Override
    @Transactional
    public void makeOrder(@NonNull String username) {
        User user = userServiceHorizontal.getUserByUsername(username);
        Cart userCart = user.getCart();

        Order createdOrder = orderFactory.create(userCart.getProducts());
        userCart.setProducts(Collections.emptySet());
        user.getOrders().add(createdOrder);
    }
}
