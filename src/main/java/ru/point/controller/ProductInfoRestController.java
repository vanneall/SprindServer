package ru.point.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.point.entity.dto.ProductDto;
import ru.point.entity.dto.ReviewDto;
import ru.point.entity.dto.CreatedReviewDto;
import ru.point.service.interfaces.ProductService;
import ru.point.service.interfaces.ReviewService;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("sprind/product")
public class ProductInfoRestController {

    private final ProductService productService;

    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ProductDto getProductByIdEndpoint(@PathVariable(name = "id") Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/{id}/reviews")
    public List<ReviewDto> getProductReviews(@PathVariable(name = "id") Long id) {
        return reviewService.getReviewsByProductId(id);
    }

    @PostMapping("/{id}/reviews")
    public void postProductReview(@PathVariable(name = "id") Long id, @RequestBody CreatedReviewDto reviewDto, Principal principal) {
        reviewService.addReview(id, reviewDto, principal.getName());
    }

}
