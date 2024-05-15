package ru.point.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.table.complex.ComplexFeedDto;
import ru.point.service.interfaces.ProductService;
import ru.point.service.interfaces.UserService;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/sprind/feed")
class FeedProductRestController {

    ProductService productService;

    UserService userService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ComplexFeedDto getProductsEndpoint(
        @RequestParam(required = false, name = "name") String request,
        Principal principal
    ) {
        String username = principal != null ? principal.getName() : null;

        List<FeedProductDto> products;
        if (request == null) {
            products = productService.getProducts(username);
        } else {
            products = productService.getProductsByName(username, request);
        }
        var address = username != null ? userService.getUserInfoByUsername(username).address() : null;
        return new ComplexFeedDto(address, products);

    }
}
