package edu.spring.review.repository;

import edu.spring.review.domain.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

    Optional<Review> findReviewById(Long id);

    List<Review> findReviewByMovieId(Long movieId);

    Optional<Review> findReviewByIdAndMovieId(Long id, Long movieId);

    @Query("{'is_liked':'true'}")
    List<Review> findReviewsByLikedIsTrue();

    void deleteReviewById(Long id);

    List<Review> findAll();
}
