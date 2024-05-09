package ru.point.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.point.entity.dto.FeedProductDto;
import ru.point.service.interfaces.CartService;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/sprind/cart")
public class CartRestController {

    CartService cartService;

    @GetMapping
    List<FeedProductDto> handleCartEndpoint(Principal user) {
        return cartService.getProductFromUserCart(user.getName());
    }

    @PutMapping("/add")
    void handleCartAddEndpoint(@RequestParam(name = "id") Long productId, Principal user) {
        cartService.addProductToCart(productId, user.getName());
    }

    @PatchMapping("/clear")
    void handleCartClearEndpoint(Principal user) {
        cartService.clearCart(user.getName());
    }
}
