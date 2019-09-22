package edu.spring.review.service.impl;

import edu.spring.review.domain.*;
import edu.spring.review.dto.MovieDTO;
import edu.spring.review.dto.RateDTO;
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
    public MovieDTO findMovieById(Long id) {
        Movie movie =
                movieRepository.findMovieById(id)
                        .orElseThrow(() -> new NotFoundException(String.format(MOVIE_NOT_FOUND, id)));

        return modelMapper.map(movie, MovieDTO.class);
    }

    @Override
    public MovieDTO findMovieByName(String name) {
        Movie movie = movieRepository.findMovieByName(name)
                .orElseThrow(() -> new NotFoundException(String.format(MOVIE_NAME_NOT_FOUND, name)));

        return modelMapper.map(movie, MovieDTO.class);
    }

    @Override
    public List<MovieDTO> findMovieByDirector(String director) {
        List<Movie> list = movieRepository.findMovieByDirector(director);

        return list
                .stream()
                .map(m -> (modelMapper.map(m, MovieDTO.class)))
                .sorted(Comparator.comparing(MovieDTO::getRateValue)).collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> findMovieByRateValue(int rate) {

        List<Movie> list = movieRepository.findMovieByRateValue(rate);
        return list
                .stream()
                .map(m -> (modelMapper.map(m, MovieDTO.class)))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> allMoviesByRating() {

        return movieRepository.findAll()
                .stream()
                .map(m -> (modelMapper.map(m, MovieDTO.class)))
                .sorted(Comparator.comparing(MovieDTO::getRateValue).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> findAll() {

        return movieRepository.findAll().stream().map(m -> (modelMapper.map(m, MovieDTO.class)))
                .collect(Collectors.toList());
    }

    @Override
    public void saveMovie(MovieDTO movieDTO) {
        Movie movie = modelMapper.map(movieDTO, Movie.class);
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

        return movieRepository.findMovieById(movieDTO.getId()).map(m -> {
            m.setName(movieDTO.getName());
            m.setCategory(movieDTO.getCategory());
            m.setShortDescription(movieDTO.getShortDescription());
            m.setDirector(movieDTO.getDirector());
            movieRepository.save(m);
            return movieDTO;
        }).orElseThrow(() -> new NotFoundException(String.format(MOVIE_NOT_FOUND, movieDTO.getId())));
    }

    @Override
    public boolean existsMovieById(Long id) {
        return movieRepository.existsMovieById(id);
    }

    @Override
    public MovieDTO addRateToMovie(RateDTO rateDTO) {

        Movie movie = movieRepository.findMovieById(rateDTO.getMovieId())
                .orElseThrow(() -> new NotFoundException(String.format(MOVIE_NOT_FOUND, rateDTO.getMovieId())));
        Rate rate = movie.getRate();
        int countVotes = rate.getCountOfAllVotes();
        int countPosVotes = rate.getCountOfPositiveVotes();
        if (rateDTO.isLiked()) {
            countPosVotes++;
        }
        int i = Math.round((float) ((10 * countPosVotes) / (countVotes + 1)));
        rate.setRateValue(i);
        rate.setCountOfPositiveVotes(countPosVotes);
        rate.setCountOfAllVotes(countVotes + 1);
        movie.setRate(rate);
        movieRepository.save(movie);

        return modelMapper.map(movie, MovieDTO.class);
    }

    @Override
    public List<MovieDTO> getMoviesByCategoryGenre(Genre genre) {
        List<Movie> list = movieRepository.getMoviesByCategoryGenre(genre);

        return list
                .stream()
                .map(m -> (modelMapper.map(m, MovieDTO.class)))
                .collect(Collectors.toList());
    }
}
