package com.codecool.TaskTiger.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Skill {
    @Id
    @SequenceGenerator(sequenceName = "skill_sequence", name= "skill_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_sequence")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "skill_worktype")
    private WorkType worktype;
    @Column(columnDefinition = "TEXT")
    private String description;
}
