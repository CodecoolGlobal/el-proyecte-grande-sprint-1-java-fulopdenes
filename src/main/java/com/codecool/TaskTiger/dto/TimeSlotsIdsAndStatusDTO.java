package com.codecool.TaskTiger.dto;

import com.codecool.TaskTiger.model.TimeSlotStatusType;
import lombok.Builder;

import java.util.List;

public record TimeSlotsIdsAndStatusDTO(List<Long> timeSlotIds, TimeSlotStatusType timeSlotStatusType) {
    @Builder
    public TimeSlotsIdsAndStatusDTO {}
}
