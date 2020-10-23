package com.example.demo.controller;

import com.example.demo.controller.requestdto.EducationRequestDTO;
import com.example.demo.model.Education;
import com.example.demo.sevice.EducationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
//TODO GTB-知识点: - 这个 @RequestMapping 放到这的作用是什么？难道是忘记删除了？
@RequestMapping
@Validated
public class EducationController {
    //TODO GTB-工程实践: - educationService 可以 final 的
    private EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    //TODO GTB-知识点: - 如果 path 部分有重复，可以提取到 class level 的 @RequestMapping 中去
    @GetMapping("/users/{id}/educations")
    @CrossOrigin
    //TODO GTB-工程实践: - 通常用 Long，而不是 primitive type 的 long
    public List<Education> getAllEducationsByUserId(@PathVariable long id) {
        return educationService.getEducationsByUserId(id);
    }

    @PostMapping("/users/{id}/educations")
    @CrossOrigin
    //TODO GTB-工程实践: + 用了 Request DTO，不错
    //TODO GTB-工程实践: - 这函数名是 copy 过来之后忘记修改了吗？
    public Education getAllEducationsByUserId(@PathVariable long id, @RequestBody @Valid EducationRequestDTO educationRequestDTO) {
        Education education = new Education(
                id, educationRequestDTO.getYear(), educationRequestDTO.getTitle(), educationRequestDTO.getDescription()
        );
        return educationService.addEducation(education);
    }
}
