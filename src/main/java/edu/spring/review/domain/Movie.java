package edu.spring.review.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "movies")
@NoArgsConstructor
public class Movie implements Serializable {

    @Id
    private Long id;

    private String name;

    private Category category;

    private String director;

    @Field("description")
    private String shortDescription;

    @DBRef
    private Rate rate;

    @DBRef
    @Field("reviews")
    private List<Review> reviewList=new ArrayList<>();
}
