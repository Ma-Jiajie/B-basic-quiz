package com.example.demo.controller;

import com.example.demo.controller.requestdto.EducationRequestDTO;
import com.example.demo.controller.responsedto.EducationResponseDTO;
import com.example.demo.model.Education;
import com.example.demo.sevice.EducationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public List<EducationResponseDTO> getAllEducationsByUserId(@PathVariable long id) {
        List<Education> educations = educationService.getEducationsByUserId(id);
        List<EducationResponseDTO> educationResponseDTOS = new ArrayList<>();
        for(Education education:educations) {
            educationResponseDTOS.add(new EducationResponseDTO(education.getYear(), education.getTitle(), education.getDescription()));
        }

        return educationResponseDTOS;
    }

    @PostMapping("/users/{id}/educations")
    public Education getAllEducationsByUserId(@PathVariable long id, @RequestBody @Valid EducationRequestDTO educationRequestDTO) {
        Education education = new Education(
                id, educationRequestDTO.getYear(), educationRequestDTO.getTitle(), educationRequestDTO.getDescription()
        );
        return educationService.addEducation(education);
    }
}
