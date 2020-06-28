package com.in28minutes.rest.webservice.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Data;

@Data
//@AllArgsConstructor
@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private String field1;
    private String field2;
    private String field3;

    public SomeBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }
}
