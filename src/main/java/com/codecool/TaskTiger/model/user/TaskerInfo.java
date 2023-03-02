package com.codecool.TaskTiger.model.user;


import com.codecool.TaskTiger.model.TaskerReview;
import com.codecool.TaskTiger.model.TimeSlot;
import com.codecool.TaskTiger.model.WorkType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;


@Getter
@Setter
@Entity(name = "taskerInfo")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskerInfo {

    @SequenceGenerator(name = "taskerInfo_sequence",
            sequenceName = "taskerInfo_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "taskerInfo_sequence")
    @Column(name = "tasker_info_id", updatable = false)
    @Id
    private Long Id;

    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "tasker_user_id", nullable = false)
    private User user;
    @JsonManagedReference
    @OneToMany(cascade = ALL, mappedBy = "tasker")
    private List<TimeSlot> timeSlotList;

    @OneToMany(cascade = ALL, mappedBy = "reviewed")
    private List<TaskerReview> taskerReviewList;

    //    @ManyToMany(cascade = ALL)
    @Enumerated(EnumType.STRING)
    private List<WorkType> skills;

}
