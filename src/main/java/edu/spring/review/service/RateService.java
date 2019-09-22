package edu.spring.review.service;


import edu.spring.review.dto.MovieDTO;

import java.util.List;

public interface RateService {

    List<MovieDTO> findMovieByRatingValue(int rate);
}
