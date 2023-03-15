package com.codecool.TaskTiger.repository;

import com.codecool.TaskTiger.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findTimeSlotByTaskerId(Long id);
}
