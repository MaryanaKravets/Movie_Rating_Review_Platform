package edu.spring.review.service.impl;

import edu.spring.review.domain.Genre;
import edu.spring.review.domain.Movie;
import edu.spring.review.domain.Rate;
import edu.spring.review.dto.MovieDTO;
import edu.spring.review.exception.Message;
import edu.spring.review.exception.NotFoundException;
import edu.spring.review.repository.MovieRepository;
import edu.spring.review.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService, Message {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;


    @Override
    public MovieDTO getMovieById(Long id) {
        Movie movie =
                movieRepository.findMovieById(id)
                        .orElseThrow(() -> new NotFoundException(String.format(MOVIE_NOT_FOUND_EXCEPTION_MESSAGE, id)));

        return convertToDto(movie);
    }

    @Override
    public MovieDTO getMovieByName(String name) {
        Movie movie = movieRepository.findMovieByName(name)
                .orElseThrow(() -> new NotFoundException(String.format(MOVIE_NAME_NOT_FOUND_EXCEPTION_MESSAGE, name)));

        return convertToDto(movie);
    }

    @Override
    public List<MovieDTO> findMovieByDirector(String director) {
        List<Movie> movieList = movieRepository.findMovieByDirector(director);

        return movieList
                .stream()
                .map(this::convertToDto)
                .sorted(Comparator.comparing(MovieDTO::getRateValue))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> findMovieByRateValue(int rate) {

        List<Movie> list = movieRepository.findMovieByRateValue(rate);
        return list
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> allMoviesByRating() {

        return movieRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .sorted(Comparator.comparing(MovieDTO::getRateValue).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> findAll() {

        return movieRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveMovie(MovieDTO movieDTO) {
        Movie movie =convertToEntity(movieDTO);
        movieRepository.save(movie);
    }

    @Override
    public void deleteMovieByName(String name) {
        movieRepository.deleteMovieByName(name);
    }

    @Override
    public void deleteMovieById(Long id) {
        movieRepository.deleteMovieById(id);

    }

    @Override
    public MovieDTO updateMovie(MovieDTO movieDTO) {

        Movie movie = movieRepository.findMovieById(movieDTO.getId())
                .orElseThrow(() -> new NotFoundException(String.format(MOVIE_NOT_FOUND_EXCEPTION_MESSAGE, movieDTO.getId())));

        movieDTO.setId(movie.getId());
        movieRepository.save(convertToEntity(movieDTO));

        return movieDTO;
    }

    @Override
    public boolean existsMovieById(Long id) {
        return movieRepository.existsMovieById(id);
    }

    @Override
    public MovieDTO addRateToMovie(Long movieId, boolean isLiked) {

        Movie movie = movieRepository.findMovieById(movieId)
                .orElseThrow(() -> new NotFoundException(String.format(MOVIE_NOT_FOUND_EXCEPTION_MESSAGE, movieId)));
        Rate rate = movie.getRate();
        int countVotes = rate.getCountOfAllVotes();
        int countPosVotes = rate.getCountOfPositiveVotes();
        if (isLiked) {
            countPosVotes++;
        }
        int i = executeRate(countVotes, countPosVotes);
        setRate(rate, countVotes + 1, countPosVotes, i);
        movie.setRate(rate);
        movieRepository.save(movie);

        return convertToDto(movie);
    }

    @Override
    public List<MovieDTO> getMoviesByCategoryGenre(Genre genre) {
        List<Movie> list = movieRepository.getMoviesByCategoryGenre(genre);

        return list
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private void setRate(Rate rate, int countOfAllVotes, int countOfPositiveVotes, int rateValue) {
        rate.setRateValue(rateValue);
        rate.setCountOfPositiveVotes(countOfPositiveVotes);
        rate.setCountOfAllVotes(countOfAllVotes);
    }

    private int executeRate(int countOfAllVotes, int countOfPositiveVotes) {
        int limitOfRating = 10;

        return Math.round((float) ((limitOfRating * countOfPositiveVotes) / (countOfAllVotes + 1)));
    }



    private MovieDTO convertToDto(Movie movie){
        MovieDTO movieDTO=modelMapper.map(movie,MovieDTO.class);
        movieDTO.setCategory(movie.getCategory().getGenre().getGenreValue());
        return movieDTO;
    }

    private Movie convertToEntity(MovieDTO movieDTO){
        Movie movie=modelMapper.map(movieDTO,Movie.class);
       movie.getCategory().setGenre(Genre.fromString(movieDTO.getCategory()));
        return movie;
    }
}
