package ru.point.utils.factory.interfaces;

import ru.point.entity.table.Product;
import ru.point.entity.table.Review;
import ru.point.entity.table.User;

public interface ReviewFactory {

    Review create(
        String advantage,
        String disadvantage,
        String description,
        Float rating,
        User user,
        Product product
    );

}
