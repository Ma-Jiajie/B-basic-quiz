package com.example.demo.controller;

import com.example.demo.controller.requestdto.UserRequestDTO;
import com.example.demo.model.User;
import com.example.demo.selfexception.UserNotFoundException;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    @MockBean
    UserService userService;
    @Autowired
    private MockMvc mockMvc;

    private User firstUser;
    private UserRequestDTO newUserRequest;
    private User newUser;

    @BeforeEach
    public void beforeEach() {
        firstUser = User.builder()
                .id(1L)
                .age(22L)
                .avatar("http://...")
                .description("爱你就像是爱生命")
                .name("mjj")
                .build();

        newUserRequest = UserRequestDTO.builder()
                .name("createUser")
                .age(99L)
                .avatar("http://...")
                .description("Good Morning")
                .build();

        newUser = User.builder()
                .id(2L)
                .age(99L)
                .avatar("http://...")
                .description("Good Morning")
                .name("createUser")
                .build();
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(userService);
    }

    @Test
    public void should_return_user_by_id_with_jsonPath() throws Exception {
        when(userService.findById(1L)).thenReturn(firstUser);
        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("mjj")));

        verify(userService, times(1)).findById(1L);
    }

    @Test
    public void should_return_User_NOT_FOUND_when_user_id_not_exist() throws Exception {
        when(userService.findById(1L)).thenThrow(new UserNotFoundException("User Not Found"));

        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("User Not Found")));

        verify(userService, times(1)).findById(1L);
    }

    @Nested
    class WhenRequestIsValid {

        @Test
        public void should_create_new_user_and_return_it() throws Exception {
            when(userService.createUser(newUserRequest)).thenReturn(newUser);

            ObjectMapper objectMapper = new ObjectMapper();
            String josnUser = objectMapper.writeValueAsString(newUser);
            mockMvc.perform(post("/users")
                    .content(josnUser).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name", is("createUser")));
        }
    }
}
