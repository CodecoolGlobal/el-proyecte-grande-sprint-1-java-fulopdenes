package com.codecool.TaskTiger.dto;

import lombok.Builder;

public record StatusDTO(String reservationStatus) {

    @Builder
    public StatusDTO {}
}
