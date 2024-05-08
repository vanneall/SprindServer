package ru.point.entity.table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id;

    @Column(name = "description")
    String description;

    @Column(name = "rating")
    Float rating;

    @ManyToOne(targetEntity = User.class)
    User user;

    @ManyToOne(targetEntity = Product.class)
    Product product;
}
