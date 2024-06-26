package ru.point.service.implementations;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.mapper.ProductToFeedProductDtoMapper;
import ru.point.entity.table.Product;
import ru.point.entity.table.User;
import ru.point.repository.interfaces.ProductRepository;
import ru.point.service.interfaces.FavoriteService;
import ru.point.service.interfaces.horizontal.UserServiceHorizontal;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final ProductToFeedProductDtoMapper productDtoMapper;
    private final UserServiceHorizontal userServiceHorizontal;
    private final ProductRepository productRepository;

    @Override
    public List<FeedProductDto> getUserFavoriteProducts(int offset, int limit, @NonNull String username) {
        User user = userServiceHorizontal.getUserByUsername(username);
        Set<Product> productsInUserCart = user.getCart().getProducts();
        return user.getFavorites()
            .stream()
            .skip(offset)
            .limit(limit)
            .map(product -> productDtoMapper.apply(
                    product,
                    true,
                    productsInUserCart.contains(product)
                )
            )
            .toList();
    }

    @Override
    @Transactional
    public void addProductByIdInFavorite(@NonNull Long id, @NonNull String username) {
        Product product = productRepository.getProductById(id);
        User user = userServiceHorizontal.getUserByUsername(username);

        user.getFavorites().add(product);
    }

    @Override
    @Transactional
    public void deleteProductByIdFromFavorite(@NonNull Long id, @NonNull String username) {
        User user = userServiceHorizontal.getUserByUsername(username);

        user.getFavorites()
            .stream()
            .filter(product -> product.getId().equals(id))
            .findFirst()
            .ifPresent(user.getFavorites()::remove);
    }
}
