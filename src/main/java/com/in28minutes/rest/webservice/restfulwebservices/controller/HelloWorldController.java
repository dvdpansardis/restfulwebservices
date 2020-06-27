package com.in28minutes.rest.webservice.restfulwebservices.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@AllArgsConstructor
public class HelloWorldController {

    private MessageSource messageSource;

    @GetMapping("/hello-world-internationalized")
    public String getMessge(@RequestHeader(name = "Accept-Language") Locale locale) {
        return messageSource.getMessage("good.morning.message", null, locale);
    }

}
