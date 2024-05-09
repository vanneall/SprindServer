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

    FavoriteService favoriteService;

    @GetMapping()
    List<FeedProductDto> handleFavoriteEndpoint(Principal principal) {
        return favoriteService.getByUsername(principal.getName());
    }

    @PostMapping()
    void handlePutFavoriteEndpoint(@RequestParam(value = "productId") Long id, Principal principal) {
        favoriteService.putFavoriteById(id, principal.getName());
    }

    @DeleteMapping()
    void handleDeleteFavoriteEndpoint(@RequestParam(value = "productId") Long id, Principal principal) {
        favoriteService.deleteFavoriteById(id, principal.getName());
    }
}