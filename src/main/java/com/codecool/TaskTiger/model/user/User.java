package com.codecool.TaskTiger.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
            name = "user_username",
            nullable = false,
            //unique = true,
            updatable = false
    )
    private String username;
    @Column(
            name = "user_firstname",
            nullable = false
    )
    private String firstName;
    @Column(
            name = "user_lastname",
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
            name = "user_registrationdate",
            updatable = false
    )
    LocalDateTime registrationDate = LocalDateTime.now();
    @Column(
            name = "user_isbanned"
    )
    private boolean isBanned = false;
    @Column(
            name = "user_isactivated"
    )
    private boolean isActivated = false;
    @Column(
            name = "user_password"
    )
    private String password;
    @Column(
            name = "user_activationdate",
            updatable = false
    )
    private LocalDateTime activationDate;
    //TODO
    private List<String> reviews = new ArrayList<>();
    //TODO
    private List<String> reservations = new ArrayList<>();
    //TODO
    private String role;
}
