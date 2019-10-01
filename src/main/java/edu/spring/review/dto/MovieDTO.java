package edu.spring.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    @Id
    private Long id;

    private String name;

    private String category;

    private String director;

    private int rateValue;

    private String shortDescription;
}
