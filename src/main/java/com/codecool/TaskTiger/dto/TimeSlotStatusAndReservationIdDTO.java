package com.codecool.TaskTiger.dto;

import com.codecool.TaskTiger.model.TimeSlotStatusType;
import lombok.Builder;

public record TimeSlotStatusAndReservationIdDTO(Long reservationId, TimeSlotStatusType timeSlotStatusType) {
    @Builder
    public TimeSlotStatusAndReservationIdDTO {}
}
