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
    @Column(name = "timeSlotId", updatable = false)
    @Id
    private long id;

    @Column
    private LocalDateTime from;

    @Column
    private LocalDateTime to;

    @Column
    private boolean isReserved;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "tasker_id")
    private TaskerInfo tasker;
}
