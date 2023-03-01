package com.codecool.TaskTiger.model.user;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    private LocalDateTime createdDate;

    private UserModel userModel;

    private UserModel tasker;

    private List<TimeSlot> reservationTimeFrame;

    private String description;
    private WorkType workType;

    private ReservationStatus reservationStatus;

    private Address address;

    private List<Message> messageList;

}
