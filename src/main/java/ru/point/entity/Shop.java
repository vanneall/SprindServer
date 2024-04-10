package ru.point.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "name", nullable = false, unique = true)
    String name;

    @Column(name = "description")
    String description;
}
