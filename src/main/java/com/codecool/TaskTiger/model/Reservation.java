package com.codecool.TaskTiger.model;

import com.codecool.TaskTiger.model.user.AppUser;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.EnumType.STRING;


@Entity(name = "Reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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

    @ManyToOne(cascade = MERGE)
    @JsonBackReference(value = "client-reservations")
    @JoinColumn(name = "client_user_id")
    private AppUser client;

    @ManyToOne(cascade = MERGE)
    @JsonBackReference(value = "tasker-reservations")
    @JoinColumn(name = "tasker_user_id")
    private TaskerInfo tasker;

    @Column(name = "description")
    private String description;

    @Enumerated(STRING)
    @Column(name = "worktype")
    private WorkType workType;

    @Column(name = "duration")
    private Double duration;

    @Column(name = "status", nullable = false)
    @Enumerated(STRING)
    private ReservationStatus reservationStatus;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Message> messageList = new ArrayList<>();

}
