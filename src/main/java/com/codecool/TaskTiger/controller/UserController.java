package com.codecool.TaskTiger.controller;

import com.codecool.TaskTiger.model.user.UserModel;
import com.codecool.TaskTiger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/all")
    public List<UserModel> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

}
