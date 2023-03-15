package com.codecool.TaskTiger.service;

import com.codecool.TaskTiger.model.TimeSlot;
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

    public List<TimeSlot> getTimeSlotByTaskerId(Long id) {
        return timeSlotRepository.findTimeSlotByTaskerId(id);
    }
}
