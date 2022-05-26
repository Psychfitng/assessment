package com.irespond.dtos;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class ResultDto {

    @NotBlank
    private String resultType;

    @NotNull
    private int maxRange;

    private String imageUrl;

    @NotBlank
    private String description;

    @NotBlank
    private String sectionId;

}
