package com.irespond.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class AssessmentQuestion {

    @Id
    private String id;

    private String questionText;

    private Option option;
}
