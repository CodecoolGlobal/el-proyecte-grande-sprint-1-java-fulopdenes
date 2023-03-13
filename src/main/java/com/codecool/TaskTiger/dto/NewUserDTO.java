package com.codecool.TaskTiger.dto;

import com.codecool.TaskTiger.model.user.Gender;

import java.time.LocalDate;

public record NewUserDTO(
        String username,
        String firstName,
        String lastName,
        String gender,
        String phoneNumber,
        LocalDate birthDate,
        String email,
        boolean isTasker,
        String password
) {}
