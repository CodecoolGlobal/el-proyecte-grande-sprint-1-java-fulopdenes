package com.codecool.TaskTiger.service;


import com.codecool.TaskTiger.dto.LoginDTO;
import com.codecool.TaskTiger.dto.NewUserDTO;
import com.codecool.TaskTiger.dto.UserMapper;
import com.codecool.TaskTiger.model.user.Gender;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.model.TimeSlot;
import com.codecool.TaskTiger.model.WorkType;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.model.user.User;
import com.codecool.TaskTiger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return userRepository.findAll();
    }

    public User getUserByUserId(Long id) {
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
    public boolean saveTimeSlots(List<TimeSlot> timeSlotList, Long id) {
        User tasker = userRepository.getUserById(id);
        TaskerInfo taskerInfo = tasker.getTaskerInfo();
        timeSlotList.forEach(timeSlot -> timeSlot.setTasker(taskerInfo));
        return true;
    }

    public User saveTaskerInfo(TaskerInfo taskerInfo, Long id){
        User tasker = userRepository.getUserById(id);
        taskerInfo.setUser(tasker);
        tasker.setTaskerInfo(taskerInfo);
        return userRepository.save(tasker);
    }

    public User getByUserNameAndPassword(LoginDTO loginDTO){
        return userRepository.getUserByUsernameAndPassword(loginDTO.userName(), loginDTO.password());
    }

    public List<User> filterUserByWorkType(WorkType workType){
    return userRepository.findAll().stream().filter(User::isTasker).filter(user -> user.getTaskerInfo()
        .getSkills().contains(workType)).collect(Collectors.toList());
    }
}
