package com.example.demo.repository;

import com.example.demo.model.Education;
import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//Todo: test fail: Failed to load ApplicationContext
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class EducationRepositoryTest {
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void should_return_education_when_id_exists() {
        User firstUser = User.builder()
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build();
        entityManager.persistAndFlush(Education.builder()
        .description("aaaa").educationId(1L).title("qqqqqq").user(firstUser).year(1990L).build());

        List<Education> found = educationRepository.findAllByUserId(1L);

        assertThat(found.size()).isNotZero();
        assertThat(found.get(0)).isEqualTo(Education.builder()
                .description("aaaa").educationId(1L).title("qqqqqq").user(firstUser).year(1990L).build());
    }
}
