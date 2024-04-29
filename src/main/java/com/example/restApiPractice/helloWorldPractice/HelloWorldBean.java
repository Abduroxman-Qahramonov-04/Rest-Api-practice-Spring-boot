package com.example.restApiPractice.helloWorldPractice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldBean {
    @GetMapping(path = "/get-bean")
    public HelloObject getObject(){
        return new HelloObject("New bean");
    }
    @GetMapping(path = "path-variable/{name}")
    public HelloObject helloWorldPathVariable(@PathVariable String name){
        return new HelloObject(name);
    }

}
