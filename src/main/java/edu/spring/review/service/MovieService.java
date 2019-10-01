package edu.spring.review.service;

import edu.spring.review.domain.Genre;
import edu.spring.review.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    MovieDTO getMovieById(Long id);

    MovieDTO getMovieByName(String name);

    List<MovieDTO> findMovieByDirector(String director);

    List<MovieDTO> findAll();

    List<MovieDTO> findMovieByRateValue(int rate);

    List<MovieDTO> allMoviesByRating();

    void saveMovie(MovieDTO movieDTO);

    void deleteMovieById(Long id);

    void deleteMovieByName(String name);

    MovieDTO updateMovie(MovieDTO movieDTO);

    boolean existsMovieById(Long id);

    MovieDTO addRateToMovie(Long movieId,boolean isLiked);

    List<MovieDTO> getMoviesByCategoryGenre(Genre genre);
}
