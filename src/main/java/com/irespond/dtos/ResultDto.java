package com.irespond.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
public class ResultDto {

    @NotBlank
    private String resultType;

    @NotNull
    private int maxRange;


    private String imageUrl;

    @NotBlank
    private String description;

}
