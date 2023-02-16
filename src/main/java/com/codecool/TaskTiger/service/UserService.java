package com.codecool.TaskTiger.service;

import com.codecool.TaskTiger.model.user.UserModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.codecool.TaskTiger.model.user.Gender.*;

@Service
public class UserService {
    private final List<UserModel> userModelList = List.of(
            new UserModel(1, "username1", "firstname1", "lastname1", LocalDate.of(2000, 3, 13), MALE, "helloka", "aa" +
                    "@gmail.com", "1231", LocalDateTime.now()),
            new UserModel(2, "username2", "firstname2", "lastname2", LocalDate.of(2000, 3, 13), FEMALE, "csumi",
                    "aa" +
                            "@gmail.com", "1232", LocalDateTime.now()),
            new UserModel(3, "username3", "firstname3", "lastname3", LocalDate.of(2000, 5, 13), MALE, "szio", "aa" +
                    "@gmail.com", "1233", LocalDateTime.now()),
            new UserModel(4, "username4", "firstname4", "lastname4", LocalDate.of(2000, 6, 13), PREFER_NOT_TO_SAY,
                    "Jackvagyok", "aa" +
                    "@gmail.com", "1234", LocalDateTime.now())
    );


    public List<UserModel> getAllUsers() {
        return List.copyOf(userModelList);
    }
}
