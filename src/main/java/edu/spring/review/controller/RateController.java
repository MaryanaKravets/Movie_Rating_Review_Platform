package edu.spring.review.controller;

import edu.spring.review.dto.MovieDTO;
import edu.spring.review.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rate")
public class RateController {

    private final RateService rateService;

    @GetMapping("/{rateValue}")
    public List<MovieDTO> findAllMovieByRating(@PathVariable(name = "rateValue") int rateValue){

        return rateService.findMovieByRatingValue(rateValue);
    }

}
