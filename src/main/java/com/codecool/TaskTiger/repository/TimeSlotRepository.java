package com.codecool.TaskTiger.repository;

import com.codecool.TaskTiger.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findTimeSlotByTaskerId(Long id);
    Optional<TimeSlot> findTimeSlotById(Long id);
    List<TimeSlot> findTimeSlotsByReservationId(Long id);
}
