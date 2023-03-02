package com.codecool.TaskTiger.repository;

import com.codecool.TaskTiger.model.Reservation;
import com.codecool.TaskTiger.model.TimeSlot;
import com.codecool.TaskTiger.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {



    List<Reservation> findAllByClient_Id(Long id);
    List<Reservation> findAllByTasker_Id(Long id);


}
