package com.codecool.TaskTiger.model.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskerModel extends UserModel {
    private String description;

    public TaskerModel(int id, String username, String firstName, String lastName, LocalDate dob, Gender gender, String email, String phoneNumber, LocalDateTime registrationDate, String description) {
        super(id, username, firstName, lastName, dob, gender, email, phoneNumber, registrationDate);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
