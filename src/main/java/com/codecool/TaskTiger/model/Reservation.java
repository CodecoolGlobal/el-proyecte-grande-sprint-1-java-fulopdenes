package com.codecool.TaskTiger.model;

import com.codecool.TaskTiger.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @SequenceGenerator(name = "reservationId_sequence",
            sequenceName = "reservationId_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "reservationId_sequence")
    @Column(name = "reservationId", updatable = false)
    private long id;
    private LocalDateTime createdDate;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "client_id")
    private User client;

    private User tasker;

    private List<TimeSlot> reservationTimeFrame;

    private String description;
    private WorkType workType;

    private ReservationStatus reservationStatus;

    private Address address;

    private List<Message> messageList;

}