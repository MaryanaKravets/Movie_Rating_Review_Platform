package edu.spring.review.controller;

import edu.spring.review.domain.Category;
import edu.spring.review.domain.Movie;
import edu.spring.review.domain.Review;
import edu.spring.review.dto.MovieDTO;
import edu.spring.review.dto.RateDTO;
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
    public MovieDTO findMovieById(@PathVariable(name = "id") Long id){

        return movieService.findMovieById(id);
    }

    @GetMapping("/name/{name}")
    public MovieDTO findMovieByName(@PathVariable(name = "name") String name){

        return movieService.findMovieByName(name);
    }

    @GetMapping("/category")
    public List<MovieDTO> findMovieByCategory(@RequestBody Category category){

        return movieService.findMovieByCategory(category);
    }

    @GetMapping("/director/{director}")
    public List<MovieDTO> findMovieByDirector(@PathVariable(name = "director") String director){

        return movieService.findMovieByDirector(director);
    }

    @GetMapping("/rate/{rate}")
    public List<MovieDTO> findMovieByRate(@PathVariable(name = "rate") int rate){

        return movieService.findMovieByRateValue(rate);
    }

    @GetMapping("/{movieId}/review")
    public List<Review> findReviewByMovieId(@PathVariable(name = "movieId") Long movieId){

        return movieService.findAllReviewByMovieId(movieId);
    }

    @GetMapping("/all")
    public List<MovieDTO> findAllMovie(){

        return movieService.findAll();
    }

    @PostMapping
    public ResponseEntity<Movie> saveMovie(@RequestBody MovieDTO movieDTO){
        movieService.saveMovie(movieDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable(name = "id") Long id){
         movieService.deleteMovieById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMovieByName(@RequestParam(name = "name") String name){
          movieService.deleteMovieByName(name);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public MovieDTO updateMovie(@RequestBody MovieDTO movieDTO){

        return movieService.updateMovie(movieDTO);
    }

    @PatchMapping("/rate")
    public ResponseEntity<RateDTO> addRateToMovie(@RequestBody RateDTO rateDTO){
        movieService.addRateToMovie(rateDTO);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
