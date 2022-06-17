package com.irespond.models;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
@Data
@Validated
public class Assessment {

    @Id
    private String id;

    @Indexed(unique = true)
    @UniqueElements
    private String title;

    @Indexed(unique = true)
    @UniqueElements
    private String standardName;

    private String category;

    private String description;

    @CreatedBy
    private LocalDateTime createdBy;

    @DBRef(lazy = false)
    private List<Section> sections = new ArrayList<>();

}
