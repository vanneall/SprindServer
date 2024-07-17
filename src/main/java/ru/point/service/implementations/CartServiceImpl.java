package ru.point.service.implementations;

import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.point.entity.OrderSummary;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.mapper.ProductToFeedProductDtoMapper;
import ru.point.entity.table.*;
import ru.point.repository.interfaces.CartRepository;
import ru.point.service.interfaces.CartService;
import ru.point.service.interfaces.horizontal.ProductServiceHorizontal;
import ru.point.service.interfaces.horizontal.UserServiceHorizontal;
import ru.point.utils.factory.interfaces.OrderFactory;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final OrderFactory orderFactory;
    private final ProductToFeedProductDtoMapper productDtoMapper;
    private final UserServiceHorizontal userServiceHorizontal;
    private final CartRepository cartRepository;
    private final ProductServiceHorizontal productServiceHorizontal;

    @Override
    @Transactional
    public List<FeedProductDto> getProductFromUserCart(int offset, int limit, String username) {
        User user = userServiceHorizontal.getUserByUsername(username);
        var favorites = user.getFavorites();

        return user.getCart().getProducts()
            .stream()
            .skip(offset)
            .limit(limit)
            .map(product -> productDtoMapper.apply(
                product,
                favorites.contains(product),
                true)
            )
            .toList();
    }

    @Override
    public void addProductToCart(Long id, String username) {
        Product product = productServiceHorizontal.getProductById(id);
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

    @Override
    @Transactional
    public OrderSummary getOrderSummary(@NonNull final String username) {
        Cart cart = userServiceHorizontal.getUserByUsername(username).getCart();
        Set<Product> productsSet = cart.getProducts();

        int delivery = productsSet.stream().mapToInt(product -> 0).sum();
        int products = productsSet.stream().mapToInt(product -> product.getPrice().getMoney().intValue()).sum();
        int discount = productsSet.stream().mapToInt(product -> 0).sum();
        int promocode = productsSet.stream().mapToInt(product -> 0).sum();
        int summary = productsSet.stream().mapToInt(product -> product.getPrice().getMoney().intValue()).sum();

        Currency currency;
        if (productsSet.stream().findFirst().isPresent()) {
            currency = productsSet.stream().findFirst().get().getPrice().getCurrency();
        } else {
            currency = null;
        }

        return new OrderSummary(delivery, products, discount, promocode, summary);
    }
}
