package ru.point.service.implementations;

import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.BootstrapRegistry;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.dto.ProductDto;
import ru.point.entity.mapper.ProductToFeedProductDtoMapper;
import ru.point.entity.mapper.ProductToProductDtoMapper;
import ru.point.entity.table.Product;
import ru.point.entity.table.User;
import ru.point.repository.interfaces.ProductRepository;
import ru.point.service.interfaces.FavoriteService;
import ru.point.service.interfaces.ProductService;
import ru.point.service.interfaces.horizontal.ProductServiceHorizontal;
import ru.point.service.interfaces.horizontal.UserServiceHorizontal;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService, ProductServiceHorizontal {

    private final ProductToFeedProductDtoMapper productDtoMapper;
    private final ProductToProductDtoMapper productToProductDtoMapper;
    private final FavoriteService favoriteService;
    private final UserServiceHorizontal userServiceHorizontal;
    private final ProductRepository productRepository;

    @Override
    public List<FeedProductDto> getProducts(
        int offset,
        int limit,
        @Nullable String username
    ) {
        List<FeedProductDto> favoriteProducts;
        Set<Product> productsInCart;

        if (username != null) {
            User user = userServiceHorizontal.getUserByUsername(username);
            favoriteProducts = favoriteService.getUserFavoriteProducts(offset, limit, username);
            productsInCart = user.getCart().getProducts();
        } else {
            favoriteProducts = Collections.emptyList();
            productsInCart = Collections.emptySet();
        }


        return productRepository.getProducts(offset, limit)
            .stream()
            .map(product -> productDtoMapper.apply(
                    product,
                    favoriteProducts.stream().anyMatch(feedProductDto -> feedProductDto.id().equals(product.getId())),
                    productsInCart.stream().anyMatch(innerProduct -> innerProduct.getId().equals(product.getId()))
                )
            ).toList();
    }

    @Override
    public List<FeedProductDto> getProductsByName(
        @NonNull String name,
        int offset,
        int limit,
        @Nullable String username
    ) {
        List<FeedProductDto> favoriteProducts;
        Set<Product> productInCarts;

        if (username != null) {
            User user = userServiceHorizontal.getUserByUsername(username);
            favoriteProducts = favoriteService.getUserFavoriteProducts(offset, limit, username);
            productInCarts = user.getCart().getProducts();
        } else {
            favoriteProducts = Collections.emptyList();
            productInCarts = Collections.emptySet();
        }

        return productRepository.getProductsByName(name, offset, limit)
            .stream()
            .map(product -> productDtoMapper.apply(
                    product,
                    favoriteProducts.stream().anyMatch(feedProductDto -> feedProductDto.id().equals(product.getId())),
                    productInCarts.stream().anyMatch(innerProduct -> innerProduct.getId().equals(product.getId()))
                )
            ).toList();
    }

    @Override
    @Transactional
    public ProductDto getProductById(@NonNull Long id, @Nullable String username) {
        boolean isFavorite = false;
        boolean isInCart = false;
        if (username != null) {
            User user = userServiceHorizontal.getUserByUsername(username);

            isInCart = user.getCart().getProducts()
                .stream()
                .anyMatch(product -> product.getId().equals(id));
            isFavorite = user.getFavorites().stream().anyMatch(product -> product.getId().equals(id));
        }

        return productToProductDtoMapper.apply(getProductById(id), isFavorite, isInCart);
    }

    @Override
    public Product getProductById(@NonNull Long id) {
        return productRepository.getProductById(id);
    }

    @Override
    public List<FeedProductDto> getProductsByCategoryCategory(int offset, int limit, Long categoryId, @Nullable String username) {
        List<FeedProductDto> favoriteProducts;
        Set<Product> productsInCart;

        if (username != null) {
            User user = userServiceHorizontal.getUserByUsername(username);
            favoriteProducts = favoriteService.getUserFavoriteProducts(offset, limit, username);
            productsInCart = user.getCart().getProducts();
        } else {
            favoriteProducts = Collections.emptyList();
            productsInCart = Collections.emptySet();
        }


        return productRepository.getProductByCategoryId(offset, limit, categoryId)
            .stream()
            .map(product -> productDtoMapper.apply(
                    product,
                    favoriteProducts.stream().anyMatch(feedProductDto -> feedProductDto.id().equals(product.getId())),
                    productsInCart.stream().anyMatch(innerProduct -> innerProduct.getId().equals(product.getId()))
                )
            ).toList();
    }
}
