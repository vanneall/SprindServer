package ru.point.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.point.entity.Product;
import ru.point.entity.dto.ProductDto;
import ru.point.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/sprind/product")
class ProductRestController {

    ProductRestController(ProductService service) {
        this.service = service;
    }

    ProductService service;

    @GetMapping()
    List<ProductDto> getProductsEndpoint() {
        return service.getProducts();
    }
}
