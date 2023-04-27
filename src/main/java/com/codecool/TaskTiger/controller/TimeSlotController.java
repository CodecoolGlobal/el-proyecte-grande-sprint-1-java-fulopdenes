package com.codecool.TaskTiger.controller;

import com.codecool.TaskTiger.dto.TimeSlotStatusAndReservationIdDTO;
import com.codecool.TaskTiger.dto.TimeSlotsIdsAndStatusDTO;
import com.codecool.TaskTiger.dto.TimeSlotsIdsAndReservationIdDTO;
import com.codecool.TaskTiger.model.TimeSlot;
import com.codecool.TaskTiger.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("timeslots")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    @Autowired
    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }


    @GetMapping("/tasker/{taskerId}")
    public List<TimeSlot> getAllTimeSlotsByTaskerID(@PathVariable Long taskerId) {
        return timeSlotService.getTimeSlotByTaskerID(taskerId);
    }

    @PutMapping("/reservation")
    public boolean addNewReservationIdToTimeSlots(
            @RequestBody TimeSlotsIdsAndReservationIdDTO timeSlotsIdsAndReservationIdDTO) {
        return timeSlotService.addNewReservationIdToTimeSlotsTable(timeSlotsIdsAndReservationIdDTO);
    }
    @PutMapping("/statusBySlotIds")
    public boolean setStatusByTimeSlotIds(@RequestBody TimeSlotsIdsAndStatusDTO timeSlotsIdsAndStatusDTO) {
        return timeSlotService.setStatusByTimeSlotIds(timeSlotsIdsAndStatusDTO);
    }

    @PutMapping("/reservation/")
    public boolean setStatusByReservationId(@RequestBody TimeSlotStatusAndReservationIdDTO timeSlotStatusAndReservationIdDTO) {
        return timeSlotService.setStatusByReservationId(timeSlotStatusAndReservationIdDTO);
    }


}
