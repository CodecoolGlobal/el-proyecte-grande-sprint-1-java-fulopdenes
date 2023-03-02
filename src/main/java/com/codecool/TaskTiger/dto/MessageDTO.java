package com.codecool.TaskTiger.dto;

import com.codecool.TaskTiger.model.Reservation;
import com.codecool.TaskTiger.model.user.User;

import java.time.LocalDateTime;

public record MessageDTO(User sender, User reciever, LocalDateTime createdDate, String message, Reservation reservation) {
}
