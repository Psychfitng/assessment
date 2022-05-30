package com.irespond.repository;

import com.irespond.models.AssessmentQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<AssessmentQuestion, String> {
}
