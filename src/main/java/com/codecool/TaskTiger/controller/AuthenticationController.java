package com.codecool.TaskTiger.controller;

import com.codecool.TaskTiger.dto.LoginDTO;
import com.codecool.TaskTiger.dto.NewUserDTO;
import com.codecool.TaskTiger.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity <String> authenticate (@RequestBody LoginDTO loginDTO){
        return ResponseEntity.ok(authenticationService.authenticate(loginDTO));
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody NewUserDTO newUserDTO) {
        return ResponseEntity.ok(authenticationService.register(newUserDTO));
    }

}
