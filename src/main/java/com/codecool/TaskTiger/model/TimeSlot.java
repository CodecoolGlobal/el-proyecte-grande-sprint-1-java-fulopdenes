package com.codecool.TaskTiger.model;


import com.codecool.TaskTiger.model.user.TaskerInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.ALL;

@Entity(name = "Timeslots")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlot {

    @SequenceGenerator(name = "timeslot_sequence",
            sequenceName = "timeslot_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "timeslot_sequence")
    @Id
    @Column(name = "timeslot_id")
    private Long id;

@Column(name="start_time")
    private LocalDateTime startTime;

    @Column(name="end_time")
    private LocalDateTime endTime;

    @Column(name="is_reserved")
    private boolean isReserved;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name="reservation_id")
    private Reservation reservation;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "tasker_user_id")
    private TaskerInfo tasker;
}
