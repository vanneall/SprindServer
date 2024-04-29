package ru.point.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.point.entity.dto.ProductDto;
import ru.point.service.interfaces.ProductService;

@AllArgsConstructor
@RestController
@RequestMapping("sprind/product")
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductDto getProductByIdEndpoint(
            @PathVariable(name = "id") Long id
    ) {
        return productService.getProductById(id);
    }

}
