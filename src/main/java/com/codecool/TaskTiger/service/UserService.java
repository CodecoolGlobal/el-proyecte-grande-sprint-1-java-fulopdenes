package com.codecool.TaskTiger.service;


import com.codecool.TaskTiger.model.TimeSlot;
import com.codecool.TaskTiger.model.WorkType;
import com.codecool.TaskTiger.model.user.AppUser;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public List<AppUser> getAllTaskers() {
        List<AppUser> taskers = new ArrayList<>();
        for (AppUser appUser : userRepository.findAll()) {
            if (appUser.isTasker()) {
                taskers.add(appUser);
            }
        }
        return taskers;
    }

    public AppUser getUserByUserId(Long id) {
        return userRepository.getUserById(id);
    }

    public boolean saveTimeSlots(List<TimeSlot> timeSlotList, Long id) {
        AppUser tasker = userRepository.getUserById(id);
        TaskerInfo taskerInfo = tasker.getTaskerInfo();
        timeSlotList.forEach(timeSlot -> timeSlot.setTasker(taskerInfo));
        return true;
    }

    public AppUser saveTaskerInfo(TaskerInfo taskerInfo, Long id) {
        AppUser tasker = userRepository.getUserById(id);
        tasker.setTaskerInfo(taskerInfo);
        return userRepository.save(tasker);
    }

    public List<AppUser> filterUserByWorkType(String workType) {
        WorkType workType1 = WorkType.valueOf(workType.toUpperCase());
        return userRepository.findAll().stream().filter(AppUser::isTasker).filter(user -> user.getTaskerInfo()
                .getSkills().contains(workType1)).collect(Collectors.toList());
    }
}
