package com.example.demo.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Education {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @NotNull
    private long year;

    @NotNull
    @Length(min = 1, max = 256, message = "tittle长度不合法")
    private String title;

    @NotNull
    @Length(min = 1, max = 4096, message = "description长度不合法")
    private String description;

    @ManyToOne
    private User user;
}
