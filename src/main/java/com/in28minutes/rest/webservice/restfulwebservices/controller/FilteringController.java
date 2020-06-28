package com.in28minutes.rest.webservice.restfulwebservices.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.in28minutes.rest.webservice.restfulwebservices.model.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue getFiltering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue getFilteringList() {
        SomeBean someBean1 = new SomeBean("value1", "value2", "value3");
        SomeBean someBean2 = new SomeBean("value1", "value2", "value3");

        List<SomeBean> someBeans = Arrays.asList(someBean1, someBean2);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBeans);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

}
