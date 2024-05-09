package ru.point.entity.mapper;

import org.springframework.stereotype.Component;
import ru.point.entity.dto.ReviewDto;
import ru.point.entity.table.Review;

import java.util.function.Function;

@Component
public final class ReviewToReviewDtoMapper implements Function<Review, ReviewDto> {
    @Override
    public ReviewDto apply(Review review) {
        return new ReviewDto(
            review.getId(),
            review.getUser().getName() + " " + review.getUser().getSecondName(),
            review.getAdvantage(),
            review.getDisadvantage(),
            review.getDescription(),
            review.getRating()
        );
    }
}
