package edu.spring.review.repository;

import edu.spring.review.domain.Genre;
import edu.spring.review.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    Optional<Movie> findMovieById(Long id);

    Optional<Movie> findMovieByName(String name);

    List<Movie> findMovieByDirector(String director);

    List<Movie> findAll();

    void deleteMovieById(Long id);

    void deleteMovieByName(String name);

    boolean existsMovieById(Long id);

    @Query("{'rate.rate_value': {$eq:?0}}")
    List<Movie> findMovieByRateValue(int rate);

    @Query("{'category.genre': ?0}")
    List<Movie> getMoviesByCategoryGenre(Genre genre);
}
