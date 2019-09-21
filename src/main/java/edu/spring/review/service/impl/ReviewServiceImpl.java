package edu.spring.review.service.impl;

import edu.spring.review.domain.Movie;
import edu.spring.review.domain.Review;
import edu.spring.review.dto.MovieDTO;
import edu.spring.review.exception.Message;
import edu.spring.review.exception.NotFoundException;
import edu.spring.review.repository.ReviewRepository;
import edu.spring.review.service.MovieService;
import edu.spring.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService, Message {

    private final ReviewRepository reviewRepository;
    private final MovieService movieService;
    private final ModelMapper modelMapper;

    @Override
    public Review findReviewById(Long id) {

        return reviewRepository.findReviewById(id)
                .orElseThrow(()->new NotFoundException(String.format(REVIEW_NOT_FOUND,id)));
    }

    @Override
    public List<Review> findReviewByMovieId(Long movieId) {
        return reviewRepository.findReviewByMovieId(movieId);
    }

    @Override
    public Review findReviewByIdAndMovieId(Long id, Long movieId) {

        return reviewRepository.findReviewByIdAndMovieId(id, movieId)
                .orElseThrow(()->new NotFoundException(String.format(MOVIE_REVIEW_NOT_FOUND,movieId,id)));
    }

    @Override
    public void deleteReviewById(Long id) {

        reviewRepository.deleteReviewById(id);
    }

    @Override
    public Review editReview(Review review) {
        Long movieId=review.getMovieId();
        Long reviewId=review.getId();
        if(! movieService.existsMovieById(movieId)){
            throw new NotFoundException(String.format(MOVIE_NOT_FOUND,movieId));
        }
        return reviewRepository.findReviewByIdAndMovieId(reviewId,movieId)
                .map(r->{r.setReviewMessage(review.getReviewMessage());
                r.setLiked(true);
                return reviewRepository.save(review);})
                .orElseThrow(()->new NotFoundException(String.format(REVIEW_NOT_FOUND,reviewId)));
    }

    @Override
    public void saveReviewToMovie(Review review) {
        MovieDTO movieDTO=movieService.findMovieById(review.getMovieId());
        Movie movie=modelMapper.map(movieDTO,Movie.class);
        movie.getReviewList().add(review);
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
