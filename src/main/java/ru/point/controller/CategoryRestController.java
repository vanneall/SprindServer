package ru.point.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.point.entity.dto.CategoryDto;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.table.complex.CategoriesInfoDto;
import ru.point.service.interfaces.CategoryService;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("sprind/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    @GetMapping("/{id}/info")
    CategoriesInfoDto getInfo(@PathVariable(name = "id") Long id) {
        return categoryService.getCategoryInfo(id);
    }

    @GetMapping("/available")
    List<CategoryDto> getAvailableCategories() {
        return categoryService.getAvailableCategories();
    }

    @GetMapping("/{id}")
    public List<FeedProductDto> handleInfoEndpoint(
        @PathVariable("id") Long categoryId,
        @RequestParam("offset") int offset,
        @RequestParam("limit") int limit,
        Principal principal
    ) {
        String username = principal != null ? principal.getName() : null;
        return categoryService.getProductsByCategoryId(offset, limit, categoryId, username);
    }
}
