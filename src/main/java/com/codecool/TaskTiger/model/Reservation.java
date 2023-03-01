package com.codecool.TaskTiger.model;

import com.codecool.TaskTiger.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;


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
    @Column(name = "reservation_id", updatable = false)
    private long id;
    @Column(name = "reservation_createdDate", nullable = false)
    private LocalDateTime createdDate;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "client_id")
    private User client;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "tasker_id")
    private User tasker;

    private List<TimeSlot> reservationTimeFrame;
    @Column(name = "reservation_desc")
    private String description;
    @Enumerated(STRING)
    @Column(name = "reservation_worktype")
    private WorkType workType;
    @Column(name = "reservation_status")
    private ReservationStatus reservationStatus;
    @OneToOne(mappedBy = "", cascade = ALL)
    private Address address;
    @OneToMany(mappedBy = "reservation", cascade = ALL)
    private List<Message> messageList;

}
