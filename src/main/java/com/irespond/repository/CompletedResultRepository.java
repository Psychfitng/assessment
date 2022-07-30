package com.irespond.repository;

import com.irespond.models.CompletedResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompletedResultRepository extends MongoRepository<CompletedResult, String> {
}
