package com.codecool.TaskTiger.model;


import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.MERGE;

@Entity(name = "Timeslot")
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
    @Column(name = "timeslot_id", nullable = false)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime start;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime end;

    @Column(name = "text_lable", nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    private TimeSlotStatusType status;

    @Column(nullable = false)
    private String backColor;

    private Long reservationId;

    @ManyToOne(cascade = MERGE)
    @JsonBackReference
    @JsonIgnoreProperties("timeSlotList")
    @JoinColumn(name = "tasker_user_id", nullable = false)
    private TaskerInfo tasker;
}
