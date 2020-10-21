package com.example.demo.sevice;

import com.example.demo.databasemock.EducationDataBaseMock;
import com.example.demo.model.Education;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    public List<Education>  getEducationsByUserId(long id){
        return EducationDataBaseMock.getEducationsByUserId(id);
    }
}
