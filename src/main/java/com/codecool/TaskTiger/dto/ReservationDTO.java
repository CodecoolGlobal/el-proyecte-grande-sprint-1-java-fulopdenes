package com.codecool.TaskTiger.dto;

import com.codecool.TaskTiger.model.*;
import com.codecool.TaskTiger.model.user.AppUser;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;


public record ReservationDTO(Integer client, Integer tasker, String description,
                             String workType, String address,
                             String message) {
    @Builder public ReservationDTO{}
}
