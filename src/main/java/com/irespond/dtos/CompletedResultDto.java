package com.irespond.dtos;

import lombok.Data;

@Data
public class CompletedResultDto {

    private String age;

    private String gender;

    private String location;

    private String occupation;

    private String aggValue;

    private String percentValue;

    private String resultType;

    private Object questionsWithValue;
}
