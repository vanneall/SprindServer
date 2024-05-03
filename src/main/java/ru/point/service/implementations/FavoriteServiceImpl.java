package ru.point.service.implementations;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.mapper.ProductToFeedProductDtoMapper;
import ru.point.entity.table.Product;
import ru.point.entity.table.User;
import ru.point.repository.interfaces.FavoriteRepository;
import ru.point.repository.interfaces.ProductRepository;
import ru.point.repository.interfaces.UsersRepository;
import ru.point.service.interfaces.FavoriteService;

import java.util.List;

@Service
@AllArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    FavoriteRepository favoriteRepository;

    UsersRepository usersRepository;

    ProductRepository productRepository;

    @Override
    public List<FeedProductDto> getByUsername(@NonNull String username) {
        return favoriteRepository.getByUsername(username)
            .stream()
            .map(product -> ProductToFeedProductDtoMapper.map(product, true))
            .toList();
    }

    //TODO переписать на использование сервиса внутри сервиса, а не репозитория
    @Override
    @Transactional
    public void putFavoriteById(Long id, @NonNull String username) {
        Product product = productRepository.getProductById(id);
        User user = usersRepository.findUserByUsername(username);

        user.getFavorites().add(product);
    }

    //TODO переписать на использование сервиса внутри сервиса, а не репозитория
    @Override
    @Transactional
    public void deleteFavoriteById(Long id, @NonNull String username) {
        User user = usersRepository.findUserByUsername(username);

        user.getFavorites()
            .stream()
            .filter(product -> product.getId().equals(id))
            .findFirst()
            .ifPresent(user.getFavorites()::remove);
    }
}
