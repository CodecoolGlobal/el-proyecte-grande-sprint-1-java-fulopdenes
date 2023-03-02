package com.codecool.TaskTiger.service;


import com.codecool.TaskTiger.dto.UserMapper;
import com.codecool.TaskTiger.model.user.User;
import com.codecool.TaskTiger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<User> getAllUsers() {
        return null;
    }

    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public long saveUser(User user) {
        User newUser = userRepository.save(user);
        return newUser.getId();
    }
}
