package com.codecool.TaskTiger.dto;

import lombok.Builder;

import java.util.List;

public record TimeSlotStatusDTO(String timeSlotStatusType, String backColor, List<Long> slotIds, Long reservationId) {
    @Builder
    public TimeSlotStatusDTO {}
}
