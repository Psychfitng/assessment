package com.irespond.repository;

import com.irespond.models.AssessmentQuestion;
import com.irespond.models.SubSection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuestionRepository extends MongoRepository<AssessmentQuestion, String> {
    List<AssessmentQuestion> findAllQuestionByCategory(SubSection category);
}
