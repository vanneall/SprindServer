package ru.point.service.implementations;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.ReviewDto;
import ru.point.entity.dto.CreatedReviewDto;
import ru.point.entity.mapper.ReviewToReviewDtoMapper;
import ru.point.entity.table.Review;
import ru.point.repository.interfaces.ProductRepository;
import ru.point.repository.interfaces.ReviewRepository;
import ru.point.repository.interfaces.UsersRepository;
import ru.point.service.interfaces.ReviewService;
import ru.point.service.interfaces.horizontal.ProductServiceHorizontal;
import ru.point.service.interfaces.horizontal.UserServiceHorizontal;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewToReviewDtoMapper reviewDtoMapper;
    private final UserServiceHorizontal userServiceHorizontal;
    private final ProductServiceHorizontal productServiceHorizontal;
    private final ReviewRepository repository;

    @Override
    public List<ReviewDto> getReviewsByProductId(@NonNull Long id) {
        return repository.getReviewByProductId(id)
            .stream()
            .map(reviewDtoMapper)
            .toList();
    }

    @Override
    @Transactional
    public void addReview(@NonNull Long productId, CreatedReviewDto reviewDto, String username) {
        Review review = new Review();
        review.setAdvantage(reviewDto.advantage());
        review.setDisadvantage(reviewDto.disadvantage());
        review.setDescription(reviewDto.description());
        review.setRating(reviewDto.rating());

        final var user = userServiceHorizontal.getUserByUsername(username);
        review.setUser(user);
        user.getReviews().add(review);

        final var product = productServiceHorizontal.getProductById(productId);
        review.setProduct(product);
        product.getReviews().add(review);
    }
}
