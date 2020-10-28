package com.example.demo.controller;

import com.example.demo.controller.requestdto.EducationRequestDTO;
import com.example.demo.model.Education;
import com.example.demo.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/users/{id}/educations")
    public List<Education> getEducationsByUserId(@PathVariable Long id) {
        return educationService.getEducationsByUserId(id);
    }

    @PostMapping("/users/{id}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public Education createEducation(@PathVariable Long id, @RequestBody @Valid EducationRequestDTO educationRequestDTO) {
        return educationService.createEducation(id, educationRequestDTO);
    }
}
