package com.codecool.TaskTiger.controller;


import com.codecool.TaskTiger.model.TimeSlot;
import com.codecool.TaskTiger.model.user.AppUser;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<AppUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/tasker/all")
    public List<AppUser> getAllTasker() {

        return userService.getAllTaskers();
    }

    @PutMapping("/taskerInfo/{id}")
    public AppUser addTaskerInfo(@PathVariable Long id, @RequestBody TaskerInfo taskerInfo) {
        return userService.saveTaskerInfo(taskerInfo, id);
    }

    @GetMapping("/{id}")
    public AppUser getUserById(@PathVariable Long id) {
        return userService.getUserByUserId(id);
    }

    @PutMapping("/timeSlot/{id}")
    public boolean saveTimeSlots(@PathVariable Long id, @RequestBody List<TimeSlot> timeSlotList) {

        return userService.saveTimeSlots(timeSlotList, id);
    }

    @GetMapping("/worktype/{worktype}")
    public List<AppUser> getUsersByWorkType(@PathVariable String worktype) {

        return userService.filterUserByWorkType(worktype);
    }

    @GetMapping("/authenticate")
    public AppUser getUserFromToken(HttpServletRequest request) {
        return userService.getUserFromToken(request);
    }
}
