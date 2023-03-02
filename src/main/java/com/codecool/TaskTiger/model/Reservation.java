package com.codecool.TaskTiger.model;

import com.codecool.TaskTiger.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;


@Entity(name = "Reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @SequenceGenerator(name = "reservationId_sequence",
            sequenceName = "reservationId_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "reservationId_sequence")
    @Column(name = "reservation_id", updatable = false)
    private Long id;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "client_user_id")
    private User client;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "tasker_user_id")
    private User tasker;

    @Column(name = "description")
    private String description;

    @Enumerated(STRING)
    @Column(name = "worktype")
    private WorkType workType;

    @Column(name = "status")
    private ReservationStatus reservationStatus;

    @OneToOne(cascade = ALL)
    private Address address;

    @OneToMany(mappedBy = "reservation", cascade = ALL)
    private List<Message> messageList;

}
