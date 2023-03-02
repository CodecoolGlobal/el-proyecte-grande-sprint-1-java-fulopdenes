package com.codecool.TaskTiger.controller;


import com.codecool.TaskTiger.model.TimeSlot;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.model.user.User;
import com.codecool.TaskTiger.service.UserService;
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
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
//    @PostMapping("/register")
//    public Long userRegistration(@RequestBody UserDTO userDTO){
//        return userService.saveUser(userDTO);
//    }
    @PostMapping("/register")
    public Long registerNewUser(@RequestBody User newUser){
        return userService.saveUser(newUser);
    }


    @PutMapping("/taskerInfo/{id}")
    public User addTaskerInfo(@PathVariable Long id, @RequestBody TaskerInfo taskerInfo){
        return userService.saveTaskerInfo(taskerInfo, id);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserByUserId(id);
    }

    @PutMapping("/timeSlot/{id}")
    public boolean saveTimeSlots(@PathVariable Long id, @RequestBody List<TimeSlot> timeSlotList){
        return userService.saveTimeSlots(timeSlotList, id);
    }


}
