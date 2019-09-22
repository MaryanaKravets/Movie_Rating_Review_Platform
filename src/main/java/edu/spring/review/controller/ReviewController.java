package edu.spring.review.controller;

import edu.spring.review.domain.Review;
import edu.spring.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public Review findReviewById(@PathVariable(name = "id") Long id) {

        return reviewService.findReviewById(id);
    }

    @GetMapping("/movie/{movieId}")
    public List<Review> findReviewsByMovieId(@PathVariable(name = "movieId") Long movieId) {

        return reviewService.findReviewByMovieId(movieId);
    }

    @GetMapping("/liked")
    public List<Review> findReviewsByLikedIsTrue() {

        return reviewService.findReviewsByLikedIsTrue();
    }

    @GetMapping("/all")
    public List<Review> findAllReview() {

        return reviewService.findAll();
    }

    @PostMapping("/movie")
    public ResponseEntity<Review> saveReview(@RequestBody Review review) {
        reviewService.saveReviewToMovie(review);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewById(@PathVariable(name = "id") Long id) {
        reviewService.deleteReviewById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public Review editReview(@RequestBody Review review) {

        return reviewService.editReview(review);
    }
}
