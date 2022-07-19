package com.irespond.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Feedback {

    @Id
    private String id;

    private String email;

    private String scale;

    private String body;
}
