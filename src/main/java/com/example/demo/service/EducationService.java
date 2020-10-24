package com.example.demo.service;

import com.example.demo.controller.requestdto.EducationRequestDTO;
import com.example.demo.model.Education;
import com.example.demo.model.User;
import com.example.demo.repository.EducationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.selfexception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EducationService {
    private final EducationRepository educationRepository;
    private final UserRepository userRepository;
    private final AtomicLong educationIdSeq = new AtomicLong();

    public EducationService(EducationRepository educationRepository, UserRepository userRepository) {
        this.educationRepository = educationRepository;
        this.userRepository = userRepository;
    }

    public List<Education> getEducationsByUserId(Long id) {
        return educationRepository.findAllByUserId(id);
    }

    public Education createEducation(Long id, EducationRequestDTO educationRequestDTO) {
        User user = userRepository.findOneById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found!"));

        Education education = new Education(
                educationIdSeq.incrementAndGet(),
                educationRequestDTO.getYear(),
                educationRequestDTO.getTitle(),
                educationRequestDTO.getDescription(),
                user
                );
        educationRepository.save(education);
        return education;
    }
}
