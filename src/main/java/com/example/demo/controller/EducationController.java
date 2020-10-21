package com.example.demo.controller;

import com.example.demo.model.Education;
import com.example.demo.sevice.EducationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@Validated
public class EducationController {
    private EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/users/{id}/educations")
    public List<Education> getAllEducationsByUserId(@PathVariable long id) {
        return educationService.getEducationsByUserId(id);
    }
}
