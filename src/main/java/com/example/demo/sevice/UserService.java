package com.example.demo.sevice;

import com.example.demo.databasemock.UserDataBaseMock;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.demo.selfexception.*;

@Service
public class UserService {
    public List<User> getAllUsers() { return UserDataBaseMock.userProvider(); }
    public User getUserById(long id) {
        Optional<User> optionalUser = UserDataBaseMock.findUserById(id);
        if(optionalUser.isEmpty()) throw new UserNotFoundException("查无此人");
        return optionalUser.get();
    }
    public User addUser(User user) {
        UserDataBaseMock.add(user);
        return user;
    }
}
