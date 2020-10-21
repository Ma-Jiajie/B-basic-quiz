package com.example.demo.controller;

import com.example.demo.controller.requestdto.UserRequestDTO;
import com.example.demo.model.User;
import com.example.demo.sevice.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable long id) throws Exception {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        long id = userService.getAllUsers().size();
        User user = new User(id+1,userRequestDTO.getName(),userRequestDTO.getAge(),userRequestDTO.getAvatar(),userRequestDTO.getDescription());
        return userService.addUser(user);
    }
}
