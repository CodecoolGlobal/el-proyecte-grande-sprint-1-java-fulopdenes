package com.codecool.TaskTiger.controller;

import com.codecool.TaskTiger.model.TimeSlot;
import com.codecool.TaskTiger.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("timeSlots")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    @Autowired
    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }
    @GetMapping("/{id}")
    public List<TimeSlot> getTaskerTimeSlotsById(@PathVariable Long id) {
        return timeSlotService.getTimeSlotByTaskerId(id);
    }

}
