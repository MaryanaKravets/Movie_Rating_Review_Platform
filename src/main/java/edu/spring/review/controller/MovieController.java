package edu.spring.review.controller;

import edu.spring.review.domain.Genre;
import edu.spring.review.domain.Movie;
import edu.spring.review.dto.MovieDTO;
import edu.spring.review.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/{id}")
    public MovieDTO getMovieById(@PathVariable(name = "id") Long id) {

        return movieService.getMovieById(id);
    }

    @GetMapping("/name/{name}")
    public MovieDTO getMovieByName(@PathVariable(name = "name") String name) {

        return movieService.getMovieByName(name);
    }

    @GetMapping("/director/{director}")
    public List<MovieDTO> findMovieByDirector(@PathVariable(name = "director") String director) {

        return movieService.findMovieByDirector(director);
    }

    @GetMapping("/rate/{rate}")
    public List<MovieDTO> findMovieByRate(@PathVariable(name = "rate") int rate) {

        return movieService.findMovieByRateValue(rate);
    }

    @GetMapping("/rating")
    public List<MovieDTO> allMoviesByRating() {

        return movieService.allMoviesByRating();
    }

    @GetMapping("/all")
    public List<MovieDTO> findAllMovie() {

        return movieService.findAll();
    }

    @PostMapping
    public ResponseEntity<Movie> saveMovie(@RequestBody MovieDTO movieDTO) {
        movieService.saveMovie(movieDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable(name = "id") Long id) {
        movieService.deleteMovieById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMovieByName(@RequestParam(name = "name") String name) {
        movieService.deleteMovieByName(name);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public MovieDTO updateMovie(@RequestBody MovieDTO movieDTO) {

        return movieService.updateMovie(movieDTO);
    }

    @PatchMapping("/{movieId}/rate")
    public MovieDTO addRateToMovie(@PathVariable(name = "movieId") Long movieId,
                                   @RequestParam boolean isLiked) {

        return movieService.addRateToMovie(movieId,isLiked);
    }

    @GetMapping
    public List<MovieDTO> findMovieByGenre(@RequestParam(name = "genre") Genre genre) {

        return movieService.getMoviesByCategoryGenre(genre);
    }
}
