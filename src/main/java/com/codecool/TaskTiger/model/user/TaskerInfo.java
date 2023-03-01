package com.codecool.TaskTiger.model.user;


import com.codecool.TaskTiger.model.TimeSlot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;


import java.util.List;


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
        private  long Id;
    @ManyToAny
    @JoinTable
    private List<TimeSlot> timeSlotList;


//    @ManyToMany
//    private List<TaskerReview> taskerReviewList;


}
