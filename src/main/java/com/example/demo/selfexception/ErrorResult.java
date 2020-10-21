package com.example.demo.selfexception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResult {
    private String timestamp;
    private String error;
    private String statusCode;
    private String message;
}
