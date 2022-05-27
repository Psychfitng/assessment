package com.irespond.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class AssessmentQuestion {

    @Id
    private String id;

    private String questionText;

    private List<Option> options = new ArrayList<>();
}
