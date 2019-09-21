package edu.spring.review.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@Document("reviews")
public class Review implements Serializable {

    @Id
    private Long id;

    @Field("movie_id")
    private Long movieId;

    @Field("text_of_review")
    private String reviewMessage;

    @Field("is_liked")
    private boolean liked;
}
