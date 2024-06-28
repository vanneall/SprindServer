package ru.point.utils.factory.implementations;

import org.springframework.stereotype.Component;
import ru.point.entity.table.Product;
import ru.point.entity.table.Review;
import ru.point.entity.table.User;
import ru.point.utils.factory.interfaces.ReviewFactory;

@Component
public class ReviewFactoryImpl implements ReviewFactory {
    @Override
    public Review create(
        String advantage,
        String disadvantage,
        String description,
        Float rating,
        User user,
        Product product
    ) {
        Review review = new Review();
        review.setAdvantage(advantage);
        review.setDisadvantage(disadvantage);
        review.setDescription(description);
        review.setRating(rating);
        review.setProduct(product);
        review.setUser(user);
        return review;
    }
}
