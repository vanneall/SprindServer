package ru.point.entity.table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ManyToMany(targetEntity = Product.class, fetch = FetchType.LAZY)
    Set<Product> products;

    @Column(name = "delivery_cost", nullable = false)
    Double deliveryCost;

    @Column(name = "products_cost", nullable = false)
    Double productsCost;

    @Transient
    @Formula(value = "delivery_cost + products_cost")
    Double summaryCost;
}
