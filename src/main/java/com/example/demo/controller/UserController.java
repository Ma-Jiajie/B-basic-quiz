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
@CrossOrigin
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
        //TODO GTB-工程实践: - 不要根据当前 users 的数量来生成id，以后有了删除功能后，会生产重复的 id 的
        long id = userService.getAllUsers().size();
        //TODO GTB-工程实践: - “id+1”？生成的时候就确定好，不要在每次使用的时候去 +1
        User user = new User(id+1,userRequestDTO.getName(),userRequestDTO.getAge(),userRequestDTO.getAvatar(),userRequestDTO.getDescription());
        return userService.addUser(user);
    }
}
