package com.irespond.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OptionDto {

    private String questionId;

    private String optionType;

    private List<String> labels;

}
