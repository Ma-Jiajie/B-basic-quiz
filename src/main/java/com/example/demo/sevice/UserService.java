package com.example.demo.sevice;

import com.example.demo.databasemock.UserDataBaseMock;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public List<User> getAllUsers() { return UserDataBaseMock.userProvider(); }
    public User getUserById(Long id) { return UserDataBaseMock.findUserById(id); }
}
