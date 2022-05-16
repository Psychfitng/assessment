package com.irespond.repository;

import com.irespond.models.AssessmentResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResultRepository extends MongoRepository<AssessmentResult, String> {
}
