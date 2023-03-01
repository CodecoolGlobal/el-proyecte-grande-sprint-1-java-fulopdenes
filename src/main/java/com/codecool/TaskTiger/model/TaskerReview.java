package com.codecool.TaskTiger.model;

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
    //    private UserModel userModel;
    private LocalDateTime createdDate;
    private int value;
    @Enumerated(EnumType.STRING)
    private WorkType workType;
    @Column(columnDefinition = "TEXT")
    private String description;
}
