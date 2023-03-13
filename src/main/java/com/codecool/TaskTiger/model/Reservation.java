package com.codecool.TaskTiger.model;

import com.codecool.TaskTiger.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinColumn(name = "client_user_id", nullable = false, updatable = false)
    private User client;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinColumn(name = "tasker_user_id", nullable = false, updatable = false)
    private User tasker;

    @Column(name = "description")
    private String description;

    @Enumerated(STRING)
    @Column(name = "worktype")
    private WorkType workType;

    @Column(name = "status", nullable = false)
    @Enumerated(STRING)
    private ReservationStatus reservationStatus;

    //    @OneToOne(cascade = ALL)
//    @JoinColumn(name = "address_id", nullable = false)
//    private Address address;
    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "reservation", cascade = ALL)
    private List<Message> messageList;

}
