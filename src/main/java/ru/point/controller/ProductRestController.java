package ru.point.controller;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.point.entity.dto.ProductDto;
import ru.point.service.interfaces.ProductService;

import java.security.Principal;

@RestController
@RequestMapping("sprind/product")
public class ProductRestController {


    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public ProductDto getProductByIdEndpoint(
            @PathVariable(name = "id") Long id,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        System.out.println(userDetails.getUsername());
        return productService.getProductById(id);
    }

}
