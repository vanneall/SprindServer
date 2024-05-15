package ru.point.entity.table;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {
    @Column(name = "city")
    String city;
    @Column(name = "street")
    String street;
    @Column(name = "house")
    String house;
    @Column(name = "flat")
    String flat;
}
