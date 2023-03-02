package com.codecool.TaskTiger.model;

import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class TaskerReview {
    @Id
    @SequenceGenerator(sequenceName = "taskerReview_sequence", name= "taskerReview_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskerReview_sequence")
    private Long id;
    @ManyToOne(cascade = ALL)
    private TaskerInfo reviewed;

    @ManyToOne(cascade = ALL)
    private User reviewer;
    private LocalDateTime createdDate;
    private int value;
    @Enumerated(EnumType.STRING)
    private WorkType workType;
    @Column(columnDefinition = "TEXT")
    private String description;
}
