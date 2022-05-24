package com.irespond.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
public class AssessmentDto {


    @NotNull
    private String title;

    @NotNull
    private String standardName;

    @NotNull
    private String category;

    @NotNull
    private String description;

    private List<String> sections;

}
