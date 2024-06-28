package ru.point.service.interfaces;

import lombok.NonNull;
import ru.point.entity.dto.FeedProductDto;

import java.util.List;

public interface FavoriteService {

    List<FeedProductDto> getUserFavoriteProducts(int offset, int limit, @NonNull String username);

    void addProductByIdInFavorite(Long id, @NonNull String username);

    void deleteProductByIdFromFavorite(Long id, @NonNull String username);
}
