package edu.spring.review.repository;

import edu.spring.review.domain.Rate;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RateRepository extends MongoRepository<Rate,String> {

//    @Query(value = "({rate: :rateValue}).stats()")
//    List<Movie> statsRating(@Param(value = "rateValue") int rateValue);
}
