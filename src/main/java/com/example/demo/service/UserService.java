package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.selfexception.UserNotFoundException;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final AtomicLong userIdSeq = new AtomicLong();
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        return userRepository.findOneById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found!"));
    }

    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

}
