package com.codecool.TaskTiger.model.user;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserModel {
    private final int id;
    private final String username;

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Gender gender;
    private String introduction;

    private String email;
    private String phoneNumber;

    LocalDateTime registrationDate;

    public UserModel(int id, String username, String firstName, String lastName, LocalDate dob, Gender gender, String introduction, String email, String phoneNumber, LocalDateTime registrationDate) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.introduction = introduction;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
    }
}
