package com.example.restApiPractice;

public class HelloObject {
    String message;

    public HelloObject(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloObject{" +
                "message='" + message + '\'' +
                '}';
    }
}
