package com.codecool.TaskTiger.repository;

import com.codecool.TaskTiger.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {


    List<TimeSlot> findAllByReservation_Id(Long id);
}
