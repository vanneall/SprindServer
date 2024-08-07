package ru.point.service.implementations;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.ReviewDto;
import ru.point.entity.dto.CreatedReviewDto;
import ru.point.entity.mapper.ReviewToReviewDtoMapper;
import ru.point.repository.interfaces.ReviewRepository;
import ru.point.service.interfaces.ReviewService;
import ru.point.service.interfaces.horizontal.ProductServiceHorizontal;
import ru.point.service.interfaces.horizontal.UserServiceHorizontal;
import ru.point.utils.factory.interfaces.ReviewFactory;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewFactory reviewFactory;
    private final ReviewToReviewDtoMapper reviewDtoMapper;
    private final UserServiceHorizontal userServiceHorizontal;
    private final ProductServiceHorizontal productServiceHorizontal;
    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDto> getReviewsByProductId(int offset, int limit, @NonNull Long id) {
        return reviewRepository.getReviewByProductId(id, PageRequest.of(offset, limit))
            .stream()
            .map(reviewDtoMapper)
            .toList();
    }

    @Override
    @Transactional
    public void addReview(@NonNull Long productId, @NonNull CreatedReviewDto reviewDto, @NonNull String username) {
        final var reviewOwnerUser = userServiceHorizontal.getUserByUsername(username);
        final var reviewedProduct = productServiceHorizontal.getProductById(productId);

        final var review = reviewFactory.create(
            reviewDto.advantage(),
            reviewDto.disadvantage(),
            reviewDto.description(),
            reviewDto.rating(),
            reviewOwnerUser,
            reviewedProduct
        );

        final float productRating = reviewedProduct.getRating();
        final int productReviewsCount = reviewedProduct.getReviewsCount();
        final float newUserReviewRating = review.getRating();
        final float newRating = (productRating * productReviewsCount + newUserReviewRating) / (productReviewsCount + 1);
        reviewedProduct.setRating(newRating);

        reviewOwnerUser.getReviews().add(review);
        reviewedProduct.getReviews().add(review);
    }
}
