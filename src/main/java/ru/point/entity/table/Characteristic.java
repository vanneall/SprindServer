package ru.point.entity.table;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@NoArgsConstructor
@Table(name = "characteristic")
public class Characteristic {

    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id;

    @Column(name = "title", nullable = false)
    String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "characteristics_description")
    @MapKeyColumn(name = "description")
    Map<String, String> descriptions;

}
