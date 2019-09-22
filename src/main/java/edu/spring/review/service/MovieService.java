package edu.spring.review.service;

import edu.spring.review.domain.Genre;
import edu.spring.review.dto.MovieDTO;
import edu.spring.review.dto.RateDTO;

import java.util.List;

public interface MovieService {

    MovieDTO findMovieById(Long id);

    MovieDTO findMovieByName(String name);

    List<MovieDTO> findMovieByDirector(String director);

    List<MovieDTO> findAll();

    List<MovieDTO> findMovieByRateValue(int rate);

    List<MovieDTO> allMoviesByRating();

    void saveMovie(MovieDTO movieDTO);

    void deleteMovieById(Long id);

    void deleteMovieByName(String name);

    MovieDTO updateMovie(MovieDTO movieDTO);

    boolean existsMovieById(Long id);

    MovieDTO addRateToMovie(RateDTO rateDTO);

    List<MovieDTO> getMoviesByCategoryGenre(Genre genre);
}
