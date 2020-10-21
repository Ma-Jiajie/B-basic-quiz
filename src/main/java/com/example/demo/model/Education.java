package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {

    @NotNull
    private long userId;

    @NotNull
    private long year;

    @NotNull
    @Length(min = 1, max = 256, message = "tittle长度不合法")
    private String title;

    @NotNull
    @Length(min = 1, max = 4096, message = "description长度不合法")
    private String description;
}
