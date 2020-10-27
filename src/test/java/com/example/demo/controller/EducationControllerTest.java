package com.example.demo.controller;

import com.example.demo.controller.requestdto.EducationRequestDTO;
import com.example.demo.controller.requestdto.UserRequestDTO;
import com.example.demo.model.Education;
import com.example.demo.model.User;
import com.example.demo.selfexception.ErrorResult;
import com.example.demo.selfexception.UserNotFoundException;
import com.example.demo.service.EducationService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;

//Todo: test fail: Failed to load ApplicationContext
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EducationControllerTest {
    @MockBean
    private EducationService educationService;
    @Autowired
    private TestRestTemplate restTemplate;

    private final List<Education> educations = new ArrayList<>();
    private EducationRequestDTO educationRequestDTO;
    private Education education;

    @BeforeEach
    public void beforeEach() {
        User firstUser = User.builder()
                .id(1L)
                .age(22L)
                .avatar("http://...")
                .description("爱你就像是爱生命")
                .name("mjj")
                .build();
        educations.add(Education.builder().user(firstUser)
                .year(1999L)
                .title("book")
                .description("sadfldsajflds;afjdshla;l.")
                .build());
        educationRequestDTO = EducationRequestDTO.builder()
                .title("new education")
                .year(2000L)
                .description("testtestetes")
                .build();
        education = Education.builder()
                .educationId(2L)
                .description("newtest")
                .title("new")
                .year(2001L)
                .user(firstUser)
                .build();
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(educationService);
    }

    @Test
    public void should_return_educationList_by_existingUserId_with_jsonPath() throws Exception {
        when(educationService.getEducationsByUserId(1L)).thenReturn(educations);

        ResponseEntity<Education> responseEntity = restTemplate.getForEntity("/educations/{id}", Education.class, 1L);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getYear()).isEqualTo(1999L);

        verify(educationService, times(1)).getEducationsByUserId(1L);
    }

    @Test
    public void should_return_educationList_by_UserId_and_throw_User_Not_Found() throws Exception {
        when(educationService.getEducationsByUserId(1L)).thenThrow(new UserNotFoundException("User Not Found"));

        //返回值不知道设置什么
        ResponseEntity<Education> responseEntity = restTemplate.getForEntity("/educations/{id}", Education.class, 1L);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(responseEntity.getBody()).isInstanceOf(ErrorResult.class);

        verify(educationService, times(1)).getEducationsByUserId(1L);
    }

    @Test
    public void should_return_education_when_create_education_with_existingUserId() throws Exception {
        when(educationService.createEducation(1L, educationRequestDTO)).thenReturn(education);

        ResponseEntity<Education> responseEntity = restTemplate.postForEntity("/educations/{id}", educationRequestDTO, Education.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getYear()).isEqualTo(2001L);

        verify(educationService, times(1)).getEducationsByUserId(1L);
    }
    @Test
    public void should_throw_User_Not_Found_when_create_education_with_UserId() throws Exception {
        when(educationService.createEducation(1L, educationRequestDTO)).thenThrow(new UserNotFoundException("User Not Found"));

        ResponseEntity<Education> responseEntity = restTemplate.postForEntity("/educations/{id}", educationRequestDTO,Education.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(Objects.requireNonNull(responseEntity.getBody())).isInstanceOf(ErrorResult.class);

        verify(educationService, times(1)).getEducationsByUserId(1L);
    }
}
