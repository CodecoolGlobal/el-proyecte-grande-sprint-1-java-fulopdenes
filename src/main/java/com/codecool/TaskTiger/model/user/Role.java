package com.codecool.TaskTiger.model.user;

import com.codecool.TaskTiger.model.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "roles")
public class Role {
    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "role_sequence"
    )
    @Column(
            name = "role_id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "role_name",
            nullable = false,
            updatable = false

    )
    @Enumerated(EnumType.STRING)
    private Roles name;
}
