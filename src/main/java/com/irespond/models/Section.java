package com.irespond.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class Section {

    @Id
    private String id;

    private String name;

    @DBRef(lazy = false)
    private List<AssessmentQuestion> questions = new ArrayList<>();

    @DBRef
    private List<AssessmentResult> results = new ArrayList<>();
}
