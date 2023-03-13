package com.codecool.TaskTiger.service;


import com.codecool.TaskTiger.dto.LoginDTO;
import com.codecool.TaskTiger.dto.NewUserDTO;
import com.codecool.TaskTiger.model.user.Gender;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.model.TimeSlot;
import com.codecool.TaskTiger.model.WorkType;
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

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllTaskers() {
        List<User> taskers = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            if (user.isTasker()) {
                taskers.add(user);
            }
        }
        return taskers;
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
        tasker.setTaskerInfo(taskerInfo);
        return userRepository.save(tasker);
    }

    public User getByUserNameAndPassword(LoginDTO loginDTO){
        return userRepository.getUserByUsernameAndPassword(loginDTO.userName(), loginDTO.password());
    }

    public List<User> filterUserByWorkType(String workType){
    WorkType workType1 = WorkType.valueOf(workType.toUpperCase());
    return userRepository.findAll().stream().filter(User::isTasker).filter(user -> user.getTaskerInfo()
        .getSkills().contains(workType1)).collect(Collectors.toList());
    }
}
