package com.irespond.repository;

import com.irespond.models.SubSection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubsectionRepo extends MongoRepository<SubSection, String> {
}
