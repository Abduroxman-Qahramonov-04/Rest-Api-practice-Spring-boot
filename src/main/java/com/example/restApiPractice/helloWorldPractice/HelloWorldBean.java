package com.example.restApiPractice.helloWorldPractice;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldBean {
    private MessageSource messageSource;
    public HelloWorldBean(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    @GetMapping(path = "/get-bean")
    public HelloObject getObject(){
        return new HelloObject("New bean");
    }
    @GetMapping(path = "path-variable/{name}")
    public HelloObject helloWorldPathVariable(@PathVariable String name){
        return new HelloObject(name);
    }
    @GetMapping(path = "/hello-in18")
    public String helloWorldIn18(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default", locale);
    }

}
