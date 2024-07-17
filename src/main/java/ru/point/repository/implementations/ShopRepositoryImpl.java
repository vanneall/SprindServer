package ru.point.repository.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ShopRepositoryImpl {

    @PersistenceContext
    private final EntityManager entityManager;


}
