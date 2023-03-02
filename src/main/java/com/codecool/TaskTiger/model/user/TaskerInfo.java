package com.codecool.TaskTiger.model.user;


import com.codecool.TaskTiger.model.TaskerReview;
import com.codecool.TaskTiger.model.TimeSlot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.util.List;

import static jakarta.persistence.CascadeType.ALL;


@Data
@Entity(name = "TaskerInfo")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskerInfo {

    @SequenceGenerator(name = "taskerInfo_sequence",
            sequenceName = "taskerInfo_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "taskerInfo_sequence")
    @Column(name = "taskerInfoId", updatable = false)
    @Id
    private long Id;

    @OneToOne()
    private User user;

    @OneToMany(cascade = ALL, mappedBy = "tasker")
    private List<TimeSlot> timeSlotList;

    @OneToMany(cascade = ALL, mappedBy = "reviewed")
    private List<TaskerReview> taskerReviewList;


}
