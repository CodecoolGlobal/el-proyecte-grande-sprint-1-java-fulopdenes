package com.codecool.TaskTiger.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import com.codecool.TaskTiger.model.user.Gender;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.model.user.User;
import com.codecool.TaskTiger.model.WorkType;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator {


    private static final Random RANDOM = new Random();

    public static User generateRandomUser(String userName, String firstName, String lastName) {
        return User.builder()
                .username(userName)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(generateRandomPhoneNumber())
                .dob(generateRandomLocalDate(1980, 2002))
                .email(generateRandomString(8) + "@example.com")
                .isTasker(true)
                .password(generateRandomString(10))
                .registrationDate(LocalDateTime.now())
                .shortIntroduction("Hi! I'm a new user at TaskTiger.")
                .gender(Gender.values()[RANDOM.nextInt(Gender.values().length)])
                .build();
    }


    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) (RANDOM.nextInt(26) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }
    public static TimeSlot generateRandomTimeSlot(User user) {
        LocalDateTime startTime = LocalDateTime.now().plusDays(RANDOM.nextInt(13)).plusHours(RANDOM.nextInt(24));
        LocalDateTime endTime = startTime.plusHours(RANDOM.nextInt(24));
        boolean isReserved = RANDOM.nextBoolean();
        System.out.println(user);
        return TimeSlot.builder()
                .startTime(startTime)
                .endTime(endTime)
                .isReserved(isReserved)
                .tasker(user.getTaskerInfo())
                .build();
    }


    private static String generateRandomPhoneNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }

    private static LocalDate generateRandomLocalDate(int startYear, int endYear) {
        int year = startYear + RANDOM.nextInt(endYear - startYear + 1);
        int month = 1 + RANDOM.nextInt(12);
        int day = 1 + RANDOM.nextInt(28);
        return LocalDate.of(year, month, day);
    }
}
