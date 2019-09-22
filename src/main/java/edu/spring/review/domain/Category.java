package edu.spring.review.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("categories")
@NoArgsConstructor
public class Category implements Serializable{

    public Genre genre;
}
