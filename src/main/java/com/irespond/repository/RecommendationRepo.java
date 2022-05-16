package com.irespond.repository;

import com.irespond.models.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecommendationRepo extends MongoRepository<Recommendation, String> {
}
