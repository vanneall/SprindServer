package ru.point.service.implementations;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.dto.ProductDto;
import ru.point.entity.mapper.ProductToFeedProductDtoMapper;
import ru.point.entity.mapper.ProductToProductDtoMapper;
import ru.point.entity.table.Product;
import ru.point.repository.interfaces.ProductRepository;
import ru.point.service.interfaces.FavoriteService;
import ru.point.service.interfaces.ProductService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private final FavoriteService favoriteService;

    private final ProductToFeedProductDtoMapper productDtoMapper;

    private final ProductToProductDtoMapper productToProductDtoMapper;

    @Override
    public List<FeedProductDto> getProducts(String username) {
        List<FeedProductDto> favorites;
        if (username != null) {
            favorites = favoriteService.getByUsername(username);
        } else {
            favorites = Collections.emptyList();
        }


        return repository.getProducts()
            .stream()
            .map(product -> productDtoMapper.apply(
                    product,
                    favorites.stream().anyMatch(feedProductDto -> feedProductDto.id().equals(product.getId()))
                )
            ).toList();
    }

    @Override
    public List<FeedProductDto> getProductsByName(String username, String name) {
        List<FeedProductDto> favorites;
        if (username != null) {
            favorites = favoriteService.getByUsername(username);
        } else {
            favorites = Collections.emptyList();
        }

        return repository.getProductsByName(name)
            .stream()
            .map(product -> productDtoMapper.apply(
                    product,
                    favorites.stream().anyMatch(feedProductDto -> feedProductDto.id().equals(product.getId()))
                )
            ).toList();
    }

    @Override
    public ProductDto getProductById(@NonNull Long id) {
        return productToProductDtoMapper.apply(repository.getProductById(id));
    }
}
