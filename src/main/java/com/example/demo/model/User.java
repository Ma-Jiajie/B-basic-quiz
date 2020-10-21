package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
public class User {
    @NotNull
    private long id;

    @NotNull
    @Length(min=1, max=128, message = "name长度不正确")
    private String name;

    @NotNull
    @Min(value = 17, message = "age长度不正确")
    private long age;

    @NotNull
    @Length(min=8 ,max = 2512, message = "avatar长度不正确")
    private String avatar;

    @Length(min=0, max = 1024, message = "description长度不正确")
    private String description;
}
