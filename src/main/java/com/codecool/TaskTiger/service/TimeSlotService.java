package com.codecool.TaskTiger.service;

import com.codecool.TaskTiger.dto.TimeSlotStatusAndReservationIdDTO;
import com.codecool.TaskTiger.dto.TimeSlotsIdsAndStatusDTO;
import com.codecool.TaskTiger.dto.TimeSlotsIdsAndReservationIdDTO;
import com.codecool.TaskTiger.model.TimeSlot;
import com.codecool.TaskTiger.model.TimeSlotStatusType;
import com.codecool.TaskTiger.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean setStatusByTimeSlotIds(TimeSlotsIdsAndStatusDTO timeSlotsIdsAndStatusDTO) {
        String backGroundColor;
        backGroundColor = switch (timeSlotsIdsAndStatusDTO.timeSlotStatusType()) {
            case RESERVED -> "#5bb7c5";
            case FREE -> "#6aa84f";
            default -> "";
        };

        for (Long currentTimeSlotId : timeSlotsIdsAndStatusDTO.timeSlotIds()) {
            TimeSlot timeSlot = timeSlotRepository.findTimeSlotById(currentTimeSlotId).orElseThrow();
            timeSlot.setBackColor(backGroundColor);
            timeSlot.setStatus(timeSlotsIdsAndStatusDTO.timeSlotStatusType());
            timeSlotRepository.save(timeSlot);
        }
        return true;
    }

    public boolean setStatusByReservationId(TimeSlotStatusAndReservationIdDTO timeSlotStatusAndReservationIdDTO) {
        List<TimeSlot> timeSlots = timeSlotRepository.findTimeSlotsByReservationId(timeSlotStatusAndReservationIdDTO.reservationId());
        TimeSlotStatusType timeSlotStatusType = timeSlotStatusAndReservationIdDTO.timeSlotStatusType();
        String backGroundColor;
        backGroundColor = switch (timeSlotStatusType) {
            case RESERVED -> "#5bb7c5";
            case FREE -> "#6aa84f";
            default -> "";
        };


        for (TimeSlot currentTimeSlot : timeSlots) {
            currentTimeSlot.setStatus(timeSlotStatusAndReservationIdDTO.timeSlotStatusType());
            currentTimeSlot.setBackColor(backGroundColor);
            if (timeSlotStatusType == TimeSlotStatusType.FREE) {
                currentTimeSlot.setReservationId(null);
            }
            timeSlotRepository.save(currentTimeSlot);
        }
        return true;
    }

     public boolean addNewReservationIdToTimeSlotsTable(TimeSlotsIdsAndReservationIdDTO timeSlotsIdsAndReservationIdDTO) {
         for (Long currentTimeSlotId : timeSlotsIdsAndReservationIdDTO.timeSlotIds()) {
             TimeSlot timeSlot = timeSlotRepository.findTimeSlotById(currentTimeSlotId).orElseThrow();
             timeSlot.setReservationId(timeSlotsIdsAndReservationIdDTO.reservationId());
             timeSlot.setBackColor("#ff6d42");
             timeSlot.setStatus(TimeSlotStatusType.PENDING);
             timeSlotRepository.save(timeSlot);
         }
         return true;
    }
}
