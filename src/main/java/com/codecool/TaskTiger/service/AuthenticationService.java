package com.codecool.TaskTiger.service;

import com.codecool.TaskTiger.dto.LoginDTO;
import com.codecool.TaskTiger.dto.NewUserDTO;
import com.codecool.TaskTiger.model.user.AppUser;
import com.codecool.TaskTiger.model.user.Gender;
import com.codecool.TaskTiger.model.user.Role;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.repository.RoleRepository;
import com.codecool.TaskTiger.repository.UserRepository;
import com.codecool.TaskTiger.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private Role getUserRole() {
        return roleRepository.findById(1L).orElseThrow();
    }

    public String register(NewUserDTO newUserDTO) {
        var newAppUser = AppUser.builder()
                .username(newUserDTO.username())
                .firstName(newUserDTO.firstName())
                .lastName(newUserDTO.lastName())
                .phoneNumber(newUserDTO.phoneNumber())
                .dob(newUserDTO.birthDate())
                .email(newUserDTO.email())
                .isTasker(newUserDTO.isTasker())
                .password(passwordEncoder.encode(newUserDTO.password()))
                .role(getUserRole()) // by default the Role is USER;
                .registrationDate(LocalDateTime.now())
                .shortIntroduction("Hi! I'm a new user at TaskTiger.")
                .gender(Gender.valueOf(newUserDTO.gender()))
                .build();

        AppUser newAppUserSaved = userRepository.save(newAppUser);

        if (newUserDTO.isTasker()) {
            TaskerInfo taskerInfo = TaskerInfo.builder()
                    .build();
            newAppUserSaved.setTaskerInfo(taskerInfo);
            userRepository.save(newAppUserSaved);
        }
        return jwtService.generateToken(newAppUserSaved);
    }

    public String authenticate(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.username(),
                        loginDTO.password()
                )
        );
        var user = userRepository.getUserByUsername(loginDTO.username()).orElseThrow();

        return jwtService.generateToken(user);
    }
}
