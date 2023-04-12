package com.codecool.TaskTiger.model.user;


import com.codecool.TaskTiger.model.Reservation;
import com.codecool.TaskTiger.model.TaskerReview;
import com.codecool.TaskTiger.model.TimeSlot;
import com.codecool.TaskTiger.model.WorkType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;


@Getter
@Setter
@Entity(name = "taskerInfo")
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class TaskerInfo {

    @SequenceGenerator(name = "taskerInfo_sequence",
            sequenceName = "taskerInfo_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "taskerInfo_sequence")
    @Column(name = "tasker_info_id", updatable = false)
    @Id
    private Long Id;

    @Column(name = "skill_info", columnDefinition = "TEXT")
    private String skillInfo;

//    @JsonManagedReference
//    @OneToMany(cascade = ALL, mappedBy = "tasker")
//    private List<TimeSlot> timeSlotList;

    private Double hourlyWage;

    @JsonManagedReference
    @JsonIgnore
    //@JsonIgnoreProperties({"reviewed, reviewer"})
    @OneToMany(cascade = ALL, mappedBy = "reviewed")
    private List<TaskerReview> taskerReviewList;

    //    @ManyToMany(cascade = ALL)
    @Enumerated(EnumType.STRING)
    private List<WorkType> skills;
    // TODO: change method of recording skills list in database.

    @OneToMany(
            cascade = {CascadeType.MERGE, REMOVE},
            mappedBy = "tasker"
    )
    @JsonManagedReference(value = "tasker-reservations")
    @JsonIgnoreProperties({"tasker", "client", "messageList"})
    private List<Reservation> reservations;

}
