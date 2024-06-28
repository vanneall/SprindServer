package ru.point.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.point.entity.dto.FeedProductDto;
import ru.point.service.interfaces.FavoriteService;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("sprind/favorites")
public class FavoriteRestController {

    private final FavoriteService favoriteService;

    @GetMapping()
    List<FeedProductDto> handleFavoriteEndpoint(
        @RequestParam(name = "offset") int offset,
        @RequestParam(name = "limit") int limit,
        Principal principal
    ) {
        return favoriteService.getUserFavoriteProducts(offset, limit, principal.getName());
    }

    @PatchMapping()
    void handlePutFavoriteEndpoint(@RequestParam(value = "id") Long id, Principal principal) {
        favoriteService.addProductByIdInFavorite(id, principal.getName());
    }

    @DeleteMapping()
    void handleDeleteFavoriteEndpoint(@RequestParam(value = "id") Long id, Principal principal) {
        favoriteService.deleteProductByIdFromFavorite(id, principal.getName());
    }
}
