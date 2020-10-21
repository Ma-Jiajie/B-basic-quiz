package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @JsonIgnore
    private long id;

    @NotNull
    @Max(value = 128, message = "")
    @Min(value = 1, message = "")
    private String name;

    @NotNull
    @Min(value = 16, message = "")
    private long age;

    @NotNull
    @Max(value = 512, message = "")
    @Min(value = 8, message = "")
    private String avatar;

    @JsonIgnore
    @Max(value = 1024, message = "")
    @Min(value = 0, message = "")
    private String description;
}
