package com.codecool.TaskTiger.dto;

import com.codecool.TaskTiger.model.Reservation;
import com.codecool.TaskTiger.model.user.User;
import lombok.Builder;

import java.time.LocalDateTime;

public record MessageDTO(User sender, User receiver, LocalDateTime createdDate, String message,
                         Reservation reservation) {
    @Builder
    public MessageDTO{}
}
