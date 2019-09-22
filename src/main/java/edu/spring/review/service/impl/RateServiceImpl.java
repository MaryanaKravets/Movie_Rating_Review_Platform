package edu.spring.review.service.impl;

import edu.spring.review.dto.MovieDTO;
import edu.spring.review.repository.RateRepository;
import edu.spring.review.service.MovieService;
import edu.spring.review.service.RateService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;
    private final MovieService movieService;
    private final ModelMapper modelMapper;

    @Override
    public List<MovieDTO> findMovieByRatingValue(int rate) {
       return rateRepository.findMovieIdByRateValue(rate).stream().map(movieService::findMovieById
        ).collect(Collectors.toList());
    }
}
