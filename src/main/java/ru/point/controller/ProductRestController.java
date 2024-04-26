package ru.point.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.point.entity.dto.ProductDto;
import ru.point.service.interfaces.ProductService;

@RestController
@RequestMapping("sprind/product")
public class ProductRestController {


    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public ProductDto getProductByIdEndpoint(
            @PathVariable(name = "id") Long id
    ) {
        return productService.getProductById(id);
    }

}
