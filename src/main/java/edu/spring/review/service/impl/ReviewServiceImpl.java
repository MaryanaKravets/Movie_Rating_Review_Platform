package edu.spring.review.service.impl;

import edu.spring.review.domain.Review;
import edu.spring.review.exception.Message;
import edu.spring.review.exception.NotFoundException;
import edu.spring.review.repository.ReviewRepository;
import edu.spring.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService, Message {

    private final ReviewRepository reviewRepository;

    @Override
    public Review getReviewById(Long id) {

        return reviewRepository.findReviewById(id)
                .orElseThrow(() -> new NotFoundException(String.format(REVIEW_NOT_FOUND_EXCEPTION_MESSAGE, id)));
    }

    @Override
    public List<Review> findReviewByMovieId(Long movieId) {

        return reviewRepository.findReviewByMovieId(movieId);
    }

    @Override
    public Review getReviewByIdAndMovieId(Long id, Long movieId) {

        return reviewRepository.findReviewByIdAndMovieId(id, movieId)
                .orElseThrow(() -> new NotFoundException(String.format(MOVIE_REVIEW_NOT_FOUND_EXCEPTION_MESSAGE, movieId, id)));
    }

    @Override
    public void deleteReviewById(Long id) {

        reviewRepository.deleteReviewById(id);
    }

    @Override
    public Review editReview(Review review) {
        Long movieId = review.getMovieId();
        Long reviewId = review.getId();

        Review review1 = reviewRepository.findReviewByIdAndMovieId(reviewId, movieId)
                .orElseThrow(() -> new NotFoundException(String.format(MOVIE_REVIEW_NOT_FOUND_EXCEPTION_MESSAGE, movieId, reviewId)));

        review1.setReviewMessage(review.getReviewMessage());
        review1.setLiked(review.isLiked());

        return reviewRepository.save(review1);
    }

    @Override
    public void saveReviewToMovie(Review review) {

        reviewRepository.save(review);
    }

    @Override
    public List<Review> findAll() {

        return reviewRepository.findAll();
    }

    @Override
    public List<Review> findReviewsByLikedIsTrue() {

        return reviewRepository.findReviewsByLikedIsTrue();
    }
}
