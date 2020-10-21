package com.example.demo.controller.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationResponseDTO {
    @NotNull
    private long year;

    @NotNull
    @Length(min = 1, max = 256, message = "tittle长度不正确")
    private String tittle;

    @NotNull
    @Length(min = 1, max = 4096, message = "description长度不正确")
    private String description;
}
