package ru.point.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Embeddable
public class Price {
    @Column(name = "money", nullable = false)
    Double money;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "currency", nullable = false)
    Currency currency;
}
