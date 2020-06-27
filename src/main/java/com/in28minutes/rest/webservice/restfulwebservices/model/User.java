package com.in28minutes.rest.webservice.restfulwebservices.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private int id;

    @Size(min = 2, message = "Name should hava atleast 2 characters")
    private String name;

    @Past
    private LocalDate birthday;

}
