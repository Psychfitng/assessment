package com.irespond.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class QuestionDto {

    @NotBlank
    private String questionText;


    private List<String> options;
}
