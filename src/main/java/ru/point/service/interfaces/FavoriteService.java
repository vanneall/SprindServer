package ru.point.service.interfaces;

import lombok.NonNull;
import ru.point.entity.dto.FeedProductDto;

import java.util.List;

public interface FavoriteService {

    List<FeedProductDto> getByUsername(@NonNull String username);

    void addProductByIdInFavorite(Long id, @NonNull String username);

    void deleteProductByIdFromFavorite(Long id, @NonNull String username);
}
