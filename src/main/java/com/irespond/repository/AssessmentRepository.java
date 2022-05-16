package com.irespond.repository;

import com.irespond.models.Assessment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface AssessmentRepository extends MongoRepository<Assessment, String> {

    @Query("{'title': ?0}")
    Optional<Assessment> findByTitle(String title);

    Optional<Assessment> findById(String id);
}
