package com.codecool.TaskTiger.controller;


import com.codecool.TaskTiger.dto.LoginDTO;
import com.codecool.TaskTiger.dto.NewUserDTO;
import com.codecool.TaskTiger.model.TimeSlot;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.model.user.AppUser;
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
    public List<AppUser> getAllUsers(){
        return userService.getAllUsers();
    }


    @GetMapping("/tasker/all")
    public List<AppUser> getAllTasker(){

        return userService.getAllTaskers();
    }



    @PostMapping("/register")
    public Long registerNewUser(@RequestBody NewUserDTO newUser){
        return userService.saveUser(newUser);
    }


    @PutMapping("/taskerInfo/{id}")
    public AppUser addTaskerInfo(@PathVariable Long id, @RequestBody TaskerInfo taskerInfo){
        return userService.saveTaskerInfo(taskerInfo, id);
    }


    @GetMapping("/{id}")
    public AppUser getUserById(@PathVariable Long id){
        return userService.getUserByUserId(id);
    }

    @PutMapping("/timeSlot/{id}")
    public boolean saveTimeSlots(@PathVariable Long id, @RequestBody List<TimeSlot> timeSlotList){

        return userService.saveTimeSlots(timeSlotList, id);
    }

//    @PostMapping("/login")
//    public AppUser getUserByNameAndPassword(@RequestBody LoginDTO loginDTO){
//        return userService.getByUserNameAndPassword(loginDTO);
//    }

    @GetMapping("/worktype/{worktype}")
    public List<AppUser> getUsersByWorkType(@PathVariable String worktype){

        return userService.filterUserByWorkType(worktype);
    }
}
