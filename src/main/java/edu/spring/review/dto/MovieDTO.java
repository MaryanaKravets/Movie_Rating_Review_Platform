package edu.spring.review.dto;

import edu.spring.review.domain.Category;

import edu.spring.review.domain.Rate;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class MovieDTO {
    @Id
    private Long id;

    private String name;

    private Category category;

    private String director;

    private Rate rate;

    private String shortDescription;
}
