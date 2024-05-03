package ru.point.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.point.entity.dto.FeedProductDto;
import ru.point.service.interfaces.ProductService;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/sprind/feed")
class FeedProductRestController {

    ProductService productService;

    //TODO убрать required = false после добавления пагинации на фронте
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<FeedProductDto> getProductsEndpoint(
            @RequestParam(required = false, name = "limit") Integer limit,
            @RequestParam(required = false, name = "offset") Integer offset,
            @RequestParam(required = false, name = "name") String request,
            Principal principal
    ) {
        String username = principal != null ? principal.getName() : null;

        if (request == null) {
            return productService.getProducts(username);
        } else {
            return productService.getProductsByName(username, request);
        }

    }
}
