package com.codecool.TaskTiger.dto;

import lombok.Builder;


public record NewReservationDTO(Integer client, Integer tasker, String description,
                                String workType, String address,
                                String message) {
    @Builder public NewReservationDTO {}
}
