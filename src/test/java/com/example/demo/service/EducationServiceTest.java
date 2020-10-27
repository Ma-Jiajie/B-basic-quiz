package com.example.demo.service;

import com.example.demo.model.Education;
import com.example.demo.model.User;
import com.example.demo.repository.EducationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.selfexception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EducationServiceTest {

    private EducationService educationService;

    @MockBean
    private UserService userService;
    @Mock
    private EducationRepository educationRepository;
    @Mock
    private UserRepository userRepository;

    private final List<Education> educations = new ArrayList<>();
    private Education education;
    private User firstUser;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
        educationService = new EducationService(educationRepository, userService);
        firstUser = User.builder()
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
        education = Education.builder()
                .educationId(1L)
                .description("newtest")
                .title("new")
                .year(2001L)
                .user(firstUser)
                .build();
    }
    @Test
    public void should_return_user_educations_when_user_id_exist() {
        when(educationRepository.findAllByUserId(1L)).thenReturn(Collections.singletonList(education));
        List<Education> educationList = educationRepository.findAllByUserId(1L);

        assertThat(educationList).isEqualTo(Collections.singletonList(education));
    }

    @Test
    void should_throw_exception() {
        when(userRepository.findOneById(222L)).thenReturn(Optional.empty());

        UserNotFoundException thrownException = assertThrows(UserNotFoundException.class, () -> {
            educationService.getEducationsByUserId(222L);
        });

        assertThat(thrownException.getMessage()).containsSequence("User Not Found");
    }
}
