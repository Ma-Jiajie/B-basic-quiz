package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {

    @NotNull
    private long year;

    @NotNull
    @Max(value = 256, message = "")
    @Min(value = 1, message = "")
    private String tittle;

    @NotNull
    @Max(value = 4096, message = "")
    @Min(value = 1, message = "")
    private String description;
}
