package ru.point.service.interfaces;

import lombok.NonNull;
import ru.point.entity.dto.ReviewDto;
import ru.point.entity.dto.ReviewPostDto;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> getReviewsByProductId(@NonNull Long id);

    void addReview(@NonNull Long productId, ReviewPostDto reviewDto, String username);

}
