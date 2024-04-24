package com.example.restApiPractice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldBean {
    @GetMapping(path = "/get-bean")
    public HelloObject getObject(){
        return new HelloObject("New bean");
    }
}
