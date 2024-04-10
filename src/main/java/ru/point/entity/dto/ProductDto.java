package ru.point.entity.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import ru.point.entity.Price;
import ru.point.entity.Product;

import java.util.List;

public record ProductDto(
        @Column(name = "id", nullable = false)
        Long id,
        @Column(name = "name", nullable = false)
        String name,
        @Column(name = "price", nullable = false)
        Price price,
        @Column(name = "description")
        String description,
        @ElementCollection
        @Column(name = "photos_url", nullable = false)
        List<String> photosUrl
) { }


