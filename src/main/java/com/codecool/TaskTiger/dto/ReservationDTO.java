package com.codecool.TaskTiger.dto;

import com.codecool.TaskTiger.model.*;
import com.codecool.TaskTiger.model.user.AppUser;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;


public record ReservationDTO(LocalDateTime createdDate, AppUser client, AppUser tasker, String description,
                             WorkType workType, ReservationStatus reservationStatus, String address,
                             List<Message> messageList) {
    @Builder public ReservationDTO{}
}
