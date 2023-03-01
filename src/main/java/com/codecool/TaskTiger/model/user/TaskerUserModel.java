package com.codecool.TaskTiger.model.user;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
public class TaskerUserModel extends UserModel {
    private final Set<String> skills;

    public TaskerUserModel(int id, String username, String firstName, String lastName, LocalDate dob, Gender gender, String introduction, String email, String phoneNumber, LocalDateTime registrationDate, Set<String> skills) {
        super(id, username, firstName, lastName, dob, gender, introduction, email, phoneNumber, registrationDate);
        this.skills = skills;
    }
}
