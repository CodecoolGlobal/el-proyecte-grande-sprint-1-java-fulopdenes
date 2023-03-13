package com.codecool.TaskTiger.dto;

import com.codecool.TaskTiger.model.Reservation;
import com.codecool.TaskTiger.model.user.AppUser;
import lombok.Builder;

import java.time.LocalDateTime;

public record MessageDTO(AppUser sender, AppUser receiver, LocalDateTime createdDate, String message,
                         Reservation reservation) {
    @Builder
    public MessageDTO{}
}
