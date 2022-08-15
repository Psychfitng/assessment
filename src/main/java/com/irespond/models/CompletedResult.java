package com.irespond.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class CompletedResult {

    @Id
    private String id;

    private String age;

    private String gender;

    private String location;

    private String occupation;

    private String aggValue;

    private String percentValue;

    private String resultType;

    private Object questionsWithValue;
}
