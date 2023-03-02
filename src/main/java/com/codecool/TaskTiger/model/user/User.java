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
    @Column(
            name = "user_registrationDate",
            updatable = false
    )
    LocalDateTime registrationDate = LocalDateTime.now();
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
            name = "user_username",
            nullable = false,
            //unique = true,
            updatable = false
    )
    private String username;
    @Column(
            name = "user_firstName",
            nullable = false
    )
    private String firstName;
    @Column(
            name = "user_lastName",
            nullable = false
    )
    private String lastName;
    @Column(
            name = "user_dob",
            nullable = false
    )
    private LocalDate dob;
    @Enumerated(STRING)
    @Column(
            name = "user_gender"
    )
    private Gender gender;
    @Column(
            name = "user_introduction",
            columnDefinition = "TEXT"
    )
    private String introduction;
    @Column(
            name = "user_email",
            unique = true,
            nullable = false
    )
    private String email;
    @Column(
            name = "user_phone",
            unique = true
    )
    private String phoneNumber;
    @Column(
            name = "user_isBanned"
    )
    private boolean isBanned = false;
    @Column(
            name = "user_isActivated"
    )
    private boolean isActivated = false;
    @Column(
            name = "user_password"
    )
    private String password;
    @Column(
            name = "user_activationDate",
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
    private Role role;

    @OneToOne()
    private TaskerInfo taskerInfo;
}
