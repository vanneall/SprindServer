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

    CartService cartService;

    UserService userService;

    @GetMapping
    ComplexFeedDto handleCartEndpoint(Principal user) {
        List<FeedProductDto> products = cartService.getProductFromUserCart(user.getName());
        var address = userService.getUserInfoByUsername(user.getName()).address();
        return new ComplexFeedDto(address, products);
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
