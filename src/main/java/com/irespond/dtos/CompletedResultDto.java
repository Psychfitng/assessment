package com.irespond.dtos;

import lombok.Data;

@Data
public class CompletedResultDto {

    private int age;

    private String gender;

    private String location;

    private String occupation;

    private int aggValue;

    private int percentValue;

    private String resultType;

    private Object questionsWithValue;
}
