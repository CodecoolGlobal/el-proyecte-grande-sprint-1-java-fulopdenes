package com.codecool.TaskTiger.service;

import com.codecool.TaskTiger.dto.UserDTO;
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

    public User getUserById(int id) {
        return null;
    }

    public long saveUser(UserDTO userDTO) {
        User newUser = userRepository.save(userMapper.mapToEntity(userDTO, User.class));
        return newUser.getId();
    }
}
