package com.irespond.dtos;

import lombok.Data;

@Data
public class FeedbackDto {

    private String email;

    private String scale;

    private String body;
}
