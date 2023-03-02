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

@Entity(name = "TaskerReview")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class TaskerReview {
    @Id
    @SequenceGenerator(sequenceName = "taskerReview_sequence", name= "taskerReview_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskerReview_sequence")
    @Column(name = "tasker_review_id", updatable = false)
    private Long id;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "reviewed_user_id", nullable = false, updatable = false)
    private TaskerInfo reviewed;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "reviewer_user_id", nullable = false, updatable = false)
    private User reviewer;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "review_value", nullable = false)
    private int reviewValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "worktype")
    private WorkType workType;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
