package com.codecool.TaskTiger.service;

import com.codecool.TaskTiger.dto.TimeSlotStatusDTO;
import com.codecool.TaskTiger.model.TimeSlot;
import com.codecool.TaskTiger.model.TimeSlotStatusType;
import com.codecool.TaskTiger.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    @Autowired
    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<TimeSlot> getTimeSlotByTaskerID(Long id) {
        return timeSlotRepository.findTimeSlotByTaskerId(id);
    }

    public Optional<TimeSlot> getTimeSlotByTimeSlotID(Long id) {
        return timeSlotRepository.findTimeSlotById(id);
    }

    public boolean modifyTimeSlotStatus(TimeSlotStatusDTO timeSlotStatusType) {
        List<Long> ids = timeSlotStatusType.slotIds();
        for (Long id : ids) {
            TimeSlot timeSlot = timeSlotRepository.findTimeSlotById(id).orElseThrow();
            TimeSlotStatusType slotStatusType = TimeSlotStatusType.valueOf(timeSlotStatusType.timeSlotStatusType().toUpperCase());
            timeSlot.setStatus(slotStatusType);
            timeSlot.setBackColor(timeSlotStatusType.backColor());
            timeSlot.setReservationId(timeSlotStatusType.reservationId());
            timeSlotRepository.save(timeSlot);
        }
        return true;
    }

    public boolean modifyTimeSlotStatusByReservationId(Long reservationId, TimeSlotStatusDTO timeSlotStatus) {
        List<TimeSlot> timeSlots = timeSlotRepository.findTimeSlotsByReservationId(reservationId);
        for (TimeSlot timeSlot : timeSlots) {
            TimeSlotStatusType slotStatusType = TimeSlotStatusType.valueOf(timeSlotStatus.timeSlotStatusType().toUpperCase());
            timeSlot.setStatus(slotStatusType);
            timeSlot.setBackColor(timeSlotStatus.backColor());
            timeSlotRepository.save(timeSlot);
        }
        return true;
    }
}
