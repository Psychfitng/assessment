package com.irespond.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class AssessmentResult {

    @Id
    private String id;

    private String resultType;

    private int maxRange;

    private String imageUrl;

    private String description;

    private List<Recommendation> recommendations = new ArrayList<>();
}
