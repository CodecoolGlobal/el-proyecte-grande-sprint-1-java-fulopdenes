package com.codecool.TaskTiger.dto;

import com.codecool.TaskTiger.model.Message;
import com.codecool.TaskTiger.model.Reservation;
import com.codecool.TaskTiger.model.user.Gender;
import com.codecool.TaskTiger.model.user.Role;
import com.codecool.TaskTiger.model.user.TaskerInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record UserDTO(
        String username,
        String firstName,
        String lastName,
        LocalDate dob,
        String email,
        String phoneNumber,
        String password,
        Boolean isTasker,
        TaskerInfo taskerInfo,
        LocalDateTime registrationDateTime,
        Gender gender,
        Role role,
        List<Reservation> reservationList,
        List<Message> messageList,
        LocalDateTime activationDate,
        Boolean isActivated,
        String shortIntroduction,
        Boolean isBanned
) {
}