package edu.spring.review.service;

import edu.spring.review.domain.Review;

import java.util.List;


public interface ReviewService {

    Review getReviewById(Long id);

    List<Review> findReviewByMovieId(Long movieId);

    Review getReviewByIdAndMovieId(Long id, Long movieId);

    void deleteReviewById(Long id);

    Review editReview(Review review);

    void saveReviewToMovie(Review review);

    List<Review> findAll();

    List<Review> findReviewsByLikedIsTrue();
}
