package com.irespond.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class Section {

    @Id
    private String id;

    private String name;

    private List<AssessmentQuestion> questions = new ArrayList<>();

    private List<AssessmentResult> result = new ArrayList<>();
}
