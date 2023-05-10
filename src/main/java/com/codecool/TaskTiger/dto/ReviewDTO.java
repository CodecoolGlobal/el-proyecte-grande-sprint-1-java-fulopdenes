package com.codecool.TaskTiger.dto;

public record ReviewDTO(
        Integer reviewed,
        Integer reviewer,
        Integer reviewValue,
        String workType,
        String description,
        Integer reservationId
) {
}
