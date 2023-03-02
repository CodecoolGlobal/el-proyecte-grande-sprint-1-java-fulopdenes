package com.codecool.TaskTiger.controller;

import com.codecool.TaskTiger.dto.UserDTO;
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
    @GetMapping("/register")
    public Long userRegistration(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

}
