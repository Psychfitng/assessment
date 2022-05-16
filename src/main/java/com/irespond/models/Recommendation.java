package com.irespond.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Recommendation {

    @Id
    private String id;

    private String title;

    private String message;

    private String imageLink;
}
