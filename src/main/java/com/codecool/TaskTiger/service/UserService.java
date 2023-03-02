package com.codecool.TaskTiger.service;


import com.codecool.TaskTiger.dto.UserMapper;
import com.codecool.TaskTiger.model.TimeSlot;
import com.codecool.TaskTiger.model.WorkType;
import com.codecool.TaskTiger.model.user.TaskerInfo;
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
        return userRepository.findAll();
    }

    public User getUserByUserId(Long id) {
        return userRepository.getUserById(id);
    }

    public long saveUser(User user) {
        User newUser = userRepository.save(user);
        return newUser.getId();
    }
    public boolean saveTimeSlots(List<TimeSlot> timeSlotList, Long id){
        User tasker = userRepository.getUserById(id);
        tasker.getTaskerInfo().getTimeSlotList().addAll(timeSlotList);
        return  true;
    }

    public User saveTaskerInfo(TaskerInfo taskerInfo, Long id){
        User tasker = userRepository.getUserById(id);
        taskerInfo.setUser(tasker);
        tasker.setTaskerInfo(taskerInfo);
        return userRepository.save(tasker);
    }


}
