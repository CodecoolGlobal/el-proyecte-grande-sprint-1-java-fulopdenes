package com.codecool.TaskTiger.repository;

import com.codecool.TaskTiger.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByReservation_Id(Long id);
}
