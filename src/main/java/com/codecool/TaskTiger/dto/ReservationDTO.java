package com.codecool.TaskTiger.dto;

import com.codecool.TaskTiger.model.*;
import com.codecool.TaskTiger.model.user.User;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;


public record ReservationDTO(LocalDateTime createdDate, User client, User tasker, String description,
                             WorkType workType, ReservationStatus reservationStatus, Address address,
                             List<Message> messageList) {
    @Builder public ReservationDTO{}
}
