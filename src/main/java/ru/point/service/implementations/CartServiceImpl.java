package ru.point.service.implementations;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.table.Product;
import ru.point.repository.interfaces.CartRepository;
import ru.point.repository.interfaces.ProductRepository;
import ru.point.service.interfaces.CartService;

import java.util.List;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    CartRepository cartRepository;

    ProductRepository productRepository;

    @Override
    public List<FeedProductDto> getProductFromUserCart(String username) {
        return cartRepository.getAllByUsername(username);
    }

    @Override
    public void addProductToCart(Long id, String username) {
        Product product = productRepository.getProductById(id);

        if (product == null) throw new EntityNotFoundException("Product with this id doesn't exist");

        cartRepository.addProduct(product, username);
    }

    @Override
    public void clearCart(String username) {
        cartRepository.clear(username);
    }
}
