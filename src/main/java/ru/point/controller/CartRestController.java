package ru.point.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.table.complex.ComplexFeedDto;
import ru.point.service.interfaces.CartService;
import ru.point.service.interfaces.UserService;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/sprind/cart")
public class CartRestController {

    private final CartService cartService;
    private final UserService userService;

    @GetMapping("/info")
    ComplexFeedDto handleInfoEndpoint(Principal principal) {
        var address = userService.getUserInfoByUsername(principal.getName()).address();
        return new ComplexFeedDto(address);
    }

    @GetMapping
    List<FeedProductDto> handleCartEndpoint(
        @RequestParam(name = "offset") int offset,
        @RequestParam(name = "limit") int limit,
        Principal principal
    ) {
        return cartService.getProductFromUserCart(offset, limit, principal.getName());
    }

    @PatchMapping()
    void handleCartAddEndpoint(@RequestParam(name = "id") Long productId, Principal user) {
        cartService.addProductToCart(productId, user.getName());
    }

    @DeleteMapping()
    void handleCartClearEndpoint(@RequestParam(name = "id", required = false) Long productId, Principal user) {
        cartService.removeProductFromCart(productId, user.getName());
    }

    @PatchMapping("/order")
    void handleCartOrderEndpoint(Principal user) {
        cartService.makeOrder(user.getName());
    }
}
