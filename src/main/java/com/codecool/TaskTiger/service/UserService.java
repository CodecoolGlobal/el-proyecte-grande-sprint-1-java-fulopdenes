package com.codecool.TaskTiger.service;


import com.codecool.TaskTiger.dto.NewUserDTO;
import com.codecool.TaskTiger.dto.UserMapper;
import com.codecool.TaskTiger.model.user.Gender;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.model.user.User;
import com.codecool.TaskTiger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public Long saveUser(NewUserDTO newUserDTO) {
        User newUser = User.builder()
                .username(newUserDTO.username())
                .firstName(newUserDTO.firstName())
                .lastName(newUserDTO.lastName())
                .phoneNumber(newUserDTO.phoneNumber())
                .dob(newUserDTO.birthDate())
                .email(newUserDTO.email())
                .isTasker(newUserDTO.isTasker())
                .password(newUserDTO.password())
                .registrationDate(LocalDateTime.now())
                .shortIntroduction("Hi! I'm a new user at TaskTiger.")
                .gender(Gender.valueOf(newUserDTO.gender()))
                .build();

        User newUserSaved = userRepository.save(newUser);

        if (newUserDTO.isTasker()) {
            TaskerInfo taskerInfo = TaskerInfo.builder()
                    .user(newUser)
                    .build();
            newUserSaved.setTaskerInfo(taskerInfo);
            userRepository.save(newUserSaved);
        }

        return newUserSaved.getId();
    }
}
