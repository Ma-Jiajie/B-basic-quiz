package com.example.demo.selfexception;

public class EducationNotFoundException extends RuntimeException {
    public EducationNotFoundException(String message) {
        super(message);
    }
}
