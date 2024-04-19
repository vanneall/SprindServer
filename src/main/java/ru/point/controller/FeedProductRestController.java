package ru.point.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.point.entity.dto.FeedProductDto;
import ru.point.service.interfaces.ProductService;

import java.util.List;

@RestController
@RequestMapping("/sprind/feed")
class FeedProductRestController {


    ProductService productService;

    FeedProductRestController(ProductService productService) {
        this.productService = productService;
    }

    //TODO убрать required = false после добавления пагинации на фронте
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<FeedProductDto> getProductsEndpoint(
            @RequestParam(required = false, name = "limit") Integer limit,
            @RequestParam(required = false, name = "offset") Integer offset,
            @RequestParam(required = false, name = "name") String name
    ) {
        if (name == null) {
            return productService.getProducts(limit, offset);
        } else {
            return productService.getProductsByName(limit, offset, name);
        }

    }
}
