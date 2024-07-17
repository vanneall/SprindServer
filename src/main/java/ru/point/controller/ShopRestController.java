package ru.point.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.point.entity.dto.FeedProductDto;
import ru.point.service.interfaces.ShopService;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("sprind/shops")
public class ShopRestController {

    private final ShopService shopService;

    @GetMapping("/{id}")
    public List<FeedProductDto> handleInfoEndpoint(
        @PathVariable("id") Long categoryId,
        @RequestParam("offset") int offset,
        @RequestParam("limit") int limit,
        Principal principal
    ) {
        String username = principal != null ? principal.getName() : null;
        return shopService.getProductsByShop(offset, limit, categoryId, username);
    }
}
