package com.codecool.TaskTiger.model;

import com.codecool.TaskTiger.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class TaskerReview {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private User user;
    private LocalDateTime createdDate;
    private int value;
    @Enumerated(EnumType.STRING)
    private WorkType workType;
    @Column(columnDefinition = "TEXT")
    private String description;
}
