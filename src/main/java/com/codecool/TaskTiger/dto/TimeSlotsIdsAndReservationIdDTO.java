package com.codecool.TaskTiger.dto;

import lombok.Builder;

import java.util.List;

public record TimeSlotsIdsAndReservationIdDTO(List<Long> timeSlotIds, Long reservationId) {

    @Builder
    public TimeSlotsIdsAndReservationIdDTO {}
}
