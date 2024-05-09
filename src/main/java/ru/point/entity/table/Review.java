package ru.point.entity.table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_review_generator")
    @SequenceGenerator(name = "default_review_generator", allocationSize = 1)
    @Column(name = "id")
    Long id;

    @Column(name = "advantage")
    String advantage;

    @Column(name = "disadvantage")
    String disadvantage;

    @Column(name = "description")
    String description;

    @NonNull
    @Column(name = "rating")
    Float rating;

    @NonNull
    @ManyToOne(targetEntity = User.class)
    User user;

    @NonNull
    @ManyToOne(targetEntity = Product.class)
    Product product;
}
