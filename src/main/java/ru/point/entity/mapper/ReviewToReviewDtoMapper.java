package ru.point.entity.mapper;

import ru.point.entity.dto.ReviewDto;
import ru.point.entity.table.Review;

public final class ReviewToReviewDtoMapper {
    private ReviewToReviewDtoMapper() {
    }

    public static ReviewDto map(Review review) {
        return new ReviewDto(
            review.getId(),
            review.getUser().getUsername(),
            review.getDescription(),
            review.getRating()
        );
    }
}
