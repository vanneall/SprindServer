package ru.point.entity.table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id;

    @OneToOne(targetEntity = User.class, mappedBy = "cart")
    User user;

    @OneToMany(targetEntity = Product.class, cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    Set<Product> products;

}
