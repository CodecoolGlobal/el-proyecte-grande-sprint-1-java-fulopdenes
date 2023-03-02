package com.codecool.TaskTiger.model.user;


import com.codecool.TaskTiger.model.Skill;
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

    @OneToOne
    @JoinColumn(name = "tasker_user_id")
    private User user;

    @OneToMany(cascade = ALL, mappedBy = "tasker")
    private List<TimeSlot> timeSlotList;

    @OneToMany(cascade = ALL, mappedBy = "reviewed")
    private List<TaskerReview> taskerReviewList;

    @ManyToMany(cascade = ALL)
    @JoinTable(
            joinColumns = @JoinColumn(name = "tasker_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;

}
