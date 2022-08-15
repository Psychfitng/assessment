package com.irespond.repository;

import com.irespond.models.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {

    List<Feedback> findByEmailIgnoreCase(String email);

    void deleteAllByEmailIgnoreCase(String email);
}
