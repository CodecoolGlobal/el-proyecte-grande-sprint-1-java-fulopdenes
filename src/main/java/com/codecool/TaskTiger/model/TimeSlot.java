package com.codecool.TaskTiger.model;


import com.codecool.TaskTiger.model.user.TaskerInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlot {

    @SequenceGenerator(name = "timeSlot_sequence",
            sequenceName = "timeSlot_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "timeSlot_sequence")
    @Id
    private Long id;

@Column(name="timeslot_from")
    private LocalDateTime from;

    @Column(name="timeslot_to")
    private LocalDateTime to;

    @Column(name="timeslot_isreserved")
    private boolean isReserved;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name="reservation_id")
    private Reservation reservation;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "tasker_id")
    private TaskerInfo tasker;
}
