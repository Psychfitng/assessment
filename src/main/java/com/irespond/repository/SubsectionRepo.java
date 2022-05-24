package com.irespond.repository;

import com.irespond.models.Section;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubsectionRepo extends MongoRepository<Section, String> {
}
