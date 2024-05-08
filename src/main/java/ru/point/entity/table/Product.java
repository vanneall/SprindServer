package ru.point.entity.table;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "price", nullable = false)
    Price price;

    @Column(name = "count", nullable = false)
    Integer count = 0;

    @Column(name = "description")
    String description;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    Set<Characteristic> characteristics;

    @ElementCollection
    @CollectionTable(name = "product_photo")
    @Column(name = "photos_url", nullable = false)
    List<String> photosUrl;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "shop_id")
    Shop shop;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "category_id")
    Category category;

    @OneToMany(targetEntity = Review.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Review> reviews;

}
