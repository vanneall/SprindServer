package ru.point.entity.table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Entity
@Getter
@Setter
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
