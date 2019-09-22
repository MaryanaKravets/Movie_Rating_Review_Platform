package edu.spring.review.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@Document(collection = "rating")
@NoArgsConstructor
public class Rate implements Serializable {

    @Id
    private Long id;

    @DBRef
    @Field("movie_id")
    private Long movieId;

    @Field("count_of_all_votes")
    private int countOfAllVotes;

    @Field("count_of_positive_votes")
    private int countOfPositiveVotes;

    @Field("rate_value")
    private int rateValue;

    public int executeRateValue(int countOfPositiveVotes, int countOfAllVotes){

        return Math.round(10*(float)(countOfPositiveVotes/countOfAllVotes));
    }
}
