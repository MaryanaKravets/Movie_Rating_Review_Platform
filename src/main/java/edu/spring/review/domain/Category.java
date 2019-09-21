package edu.spring.review.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("categories")
@NoArgsConstructor
public class Category implements Serializable{

    @Id
    private int id;

    public Genre genre;
}
