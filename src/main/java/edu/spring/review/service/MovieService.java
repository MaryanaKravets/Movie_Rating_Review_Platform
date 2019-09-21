package edu.spring.review.service;

import edu.spring.review.domain.Category;
import edu.spring.review.domain.Review;
import edu.spring.review.dto.MovieDTO;
import edu.spring.review.dto.RateDTO;

import java.util.List;

public interface MovieService {

    MovieDTO findMovieById(Long id);

    MovieDTO findMovieByName(String name);

    List<MovieDTO> findMovieByCategory(Category category);

    List<MovieDTO> findMovieByDirector(String director);

    List<MovieDTO> findMovieByRateValue(int rate);

    List<MovieDTO> findAll();

    List<Review> findAllReviewByMovieId(Long movieId);

    void saveMovie(MovieDTO movieDTO);

    void deleteMovieById(Long id);

    void deleteMovieByName(String name);

    MovieDTO updateMovie(MovieDTO movieDTO);

    boolean existsMovieById(Long id);

    void addRateToMovie(RateDTO rateDTO);
}
