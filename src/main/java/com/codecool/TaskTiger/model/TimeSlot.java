package com.codecool.TaskTiger.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
}
