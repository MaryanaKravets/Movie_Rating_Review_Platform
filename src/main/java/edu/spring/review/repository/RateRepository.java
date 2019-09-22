package edu.spring.review.repository;

import edu.spring.review.domain.Rate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RateRepository extends MongoRepository<Rate,String> {

    @Query("{'rate_value':?0}")
    List<Long> findMovieIdByRateValue(int rate);
}
