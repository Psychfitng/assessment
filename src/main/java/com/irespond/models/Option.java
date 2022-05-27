package com.irespond.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class Option {
    @Id
    private String id;

    private OptionType optionType;

    private List<String> labels;

    private Integer scaleValue;
}
