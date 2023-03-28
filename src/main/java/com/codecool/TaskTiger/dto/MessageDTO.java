package com.codecool.TaskTiger.dto;

import com.codecool.TaskTiger.model.Reservation;
import com.codecool.TaskTiger.model.user.AppUser;
import lombok.Builder;

import java.time.LocalDateTime;

public record MessageDTO(Integer senderId, Integer receiverId, LocalDateTime createdDate, String message,
                         Integer reservationId) {
    @Builder
    public MessageDTO {}
}