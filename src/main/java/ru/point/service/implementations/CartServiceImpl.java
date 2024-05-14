package ru.point.service.implementations;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.mapper.ProductToFeedProductDtoMapper;
import ru.point.entity.table.Cart;
import ru.point.entity.table.Order;
import ru.point.entity.table.Product;
import ru.point.entity.table.User;
import ru.point.repository.interfaces.CartRepository;
import ru.point.repository.interfaces.ProductRepository;
import ru.point.repository.interfaces.UsersRepository;
import ru.point.service.interfaces.CartService;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    CartRepository cartRepository;

    ProductRepository productRepository;

    ProductToFeedProductDtoMapper productDtoMapper;

    UsersRepository usersRepository;

    @Override
    public List<FeedProductDto> getProductFromUserCart(String username) {
        return cartRepository.getAllByUsername(username)
            .stream()
            .map(product -> productDtoMapper.apply(product, false, true))
            .toList();
    }

    @Override
    public void addProductToCart(Long id, String username) {
        Product product = productRepository.getProductById(id);

        if (product == null) throw new EntityNotFoundException("Product with this id doesn't exist");

        cartRepository.addProduct(product, username);
    }

    @Override
    public void removeProductFromCart(Long productId, String username) {
        if (productId == null) {
            cartRepository.clear(username);
        } else {
            cartRepository.deleteById(productId, username);
        }

    }

    @Override
    @Transactional
    public void makeOrder(@NonNull String username) {
        User user = usersRepository.findUserByUsername(username);
        Cart userCart = user.getCart();

        Order order = new Order();
        order.setProducts(userCart.getProducts());
        order.setDeliveryCost(0.0);
        order.setProductsCost(
            userCart.getProducts()
                .stream()
                .mapToDouble(product -> product.getPrice().getMoney())
                .sum()
        );

        userCart.setProducts(Collections.emptySet());
        user.getOrders().add(order);
    }
}
