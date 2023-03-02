package com.codecool.TaskTiger.model.user;

import com.codecool.TaskTiger.model.ClientReview;
import com.codecool.TaskTiger.model.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "user_id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "username",
            nullable = false,
            unique = true,
            updatable = false
    )
    private String username;

    @Column(
            name = "firstName",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "lastName",
            nullable = false
    )
    private String lastName;

    @Column(
            name = "dob",
            nullable = false
    )
    private LocalDate dob;

    @Enumerated(STRING)
    @Column(
            name = "gender"
    )
    private Gender gender;

    @Column(
            name = "introduction",
            columnDefinition = "TEXT"
    )
    private String introduction;

    @Column(
            name = "email",
            unique = true,
            nullable = false
    )
    private String email;

    @Column(
            name = "phone",
            unique = true
    )
    private String phoneNumber;

    @Column(
            name = "isBanned"
    )
    private boolean isBanned;

    @Column(
            name = "isActivated"
    )
    private boolean isActivated;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @Column(
            name = "activation_date",
            updatable = false
    )
    private LocalDateTime activationDate;

    @OneToMany(
            cascade = ALL,
            mappedBy = "reviewedUser")
    private List<ClientReview> reviews;

    @OneToMany(
            cascade = ALL,
            mappedBy = "client"
    )
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private Role role;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "tasker_info_id")
    private TaskerInfo taskerInfo;

    @Column(
            name = "registration_date",
            updatable = false
    )
    LocalDateTime registrationDate;

    @Column(name = "is_tasker", nullable = false)
    private boolean isTasker;
}
