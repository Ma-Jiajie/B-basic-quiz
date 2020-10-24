package com.example.demo.service;

import com.example.demo.model.Education;
import com.example.demo.repository.EducationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public List<Education> getEducationsByUserId(Long id) {
        return educationRepository.findAllByUserId(id);
    }

    public Education createEducation(Education education) {
        educationRepository.save(education);
        return education;
    }
}
