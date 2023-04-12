package com.codecool.TaskTiger.controller;

import com.codecool.TaskTiger.dto.TimeSlotStatusDTO;
import com.codecool.TaskTiger.model.TimeSlot;
import com.codecool.TaskTiger.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("timeslots")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    @Autowired
    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    // TODO: simplify endpoint paths.

    @GetMapping("/tasker/{id}")
    public List<TimeSlot> getTimeSlotByTaskerID(@PathVariable Long id) {
        return timeSlotService.getTimeSlotByTaskerID(id);
    }

    @GetMapping("/tasker/slot/{id}")
    public Optional<TimeSlot> getTimeSlotByID(@PathVariable Long id) {
        return timeSlotService.getTimeSlotByTimeSlotID(id);
    }

    @PutMapping("/tasker/slot/")
    public boolean modifyTimeSlotStatus(@RequestBody TimeSlotStatusDTO timeSlotStatus) {
        return timeSlotService.modifyTimeSlotStatus(timeSlotStatus);
    }

    @PutMapping("/tasker/reservation/modify/{reservationId}")
    public boolean modifyTimeSlotStatusByReservationId(
            @RequestBody TimeSlotStatusDTO timeSlotStatus,
            @PathVariable Long reservationId) {
        return timeSlotService.modifyTimeSlotStatusByReservationId(reservationId, timeSlotStatus);
    }

}
