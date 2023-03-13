package com.codecool.TaskTiger.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;


import com.codecool.TaskTiger.model.user.Gender;
import com.codecool.TaskTiger.model.user.AppUser;
import com.codecool.TaskTiger.security.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator {


    private static PasswordConfig passwordConfig = new PasswordConfig();



    private static final Random RANDOM = new Random();

    public static AppUser generateRandomUser(String userName, String firstName, String lastName) {
        return AppUser.builder()
                .username(userName)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(generateRandomPhoneNumber())
                .isActivated(true)
                .dob(generateRandomLocalDate(1980, 2002))
                .email(generateRandomString(8) + "@example.com")
                .isTasker(true)
                .password(passwordConfig.passwordEncoder().encode("password"))
                .registrationDate(LocalDateTime.now())
                .shortIntroduction("Hi! I'm a new user at TaskTiger.")
                .gender(Gender.values()[RANDOM.nextInt(Gender.values().length)])
                .build();
    }

//                    .password(passwordConfig.passwordEncoder().encode(generateRandomString(10)))



    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) (RANDOM.nextInt(26) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }
    public static TimeSlot generateRandomTimeSlot(AppUser appUser) {
        LocalDateTime startTime = LocalDateTime.now().plusDays(RANDOM.nextInt(13)).plusHours(RANDOM.nextInt(24));
        LocalDateTime endTime = startTime.plusHours(RANDOM.nextInt(24));
        boolean isReserved = RANDOM.nextBoolean();
        return TimeSlot.builder()
                .startTime(startTime)
                .endTime(endTime)
                .isReserved(isReserved)
                .tasker(appUser.getTaskerInfo())
                .build();
    }
    private static final String[] COUNTRIES = {"Hungary", "USA", "Germany", "France", "UK"};
    private static final String[] ZIPCODES = {"1011", "2000", "1234", "5678", "9999"};
    private static final String[] COUNTIES = {"Budapest", "New York", "Berlin", "Paris", "London"};
    private static final String[] CITIES = {"Budapest", "New York", "Berlin", "Paris", "London"};
    private static final String[] STREETS = {"Main Street", "Broadway", "Fifth Avenue", "Champs-Élysées", "Oxford Street"};



    public static Address generateRandomAddress() {
        String country = COUNTRIES[RANDOM.nextInt(COUNTRIES.length)];
        String zipcode = ZIPCODES[RANDOM.nextInt(ZIPCODES.length)];
        String county = COUNTIES[RANDOM.nextInt(COUNTIES.length)];
        String city = CITIES[RANDOM.nextInt(CITIES.length)];
        String street = STREETS[RANDOM.nextInt(STREETS.length)];
        int streetNumber = RANDOM.nextInt(100) + 1;
        String building = null;
        if (RANDOM.nextBoolean()) {
            building = "Building " + (RANDOM.nextInt(20) + 1);
        }
        return Address.builder()
                .country(country)
                .zipcode(zipcode)
                .county(county)
                .city(city)
                .street(street)
                .street_nr(streetNumber)
                .building(building)
                .build();
    }


    public static Reservation generateRandomReservation(AppUser client, AppUser tasker, Address address, WorkType workType) {
        LocalDateTime createdDate = LocalDateTime.now();
        String description = "Random description";
        ;
        String country = COUNTRIES[RANDOM.nextInt(COUNTRIES.length)];
        String zipcode = ZIPCODES[RANDOM.nextInt(ZIPCODES.length)];
        String county = COUNTIES[RANDOM.nextInt(COUNTIES.length)];
        String city = CITIES[RANDOM.nextInt(CITIES.length)];
        String street = STREETS[RANDOM.nextInt(STREETS.length)];
        int streetNumber = RANDOM.nextInt(100) + 1;
        ReservationStatus reservationStatus = ReservationStatus.PENDING;

        return Reservation.builder()
                .createdDate(createdDate)
                .client(client)
                .tasker(tasker)
                .description(description)
                .workType(workType)
                .reservationStatus(reservationStatus)
                .address(country + " "+ zipcode + " " + county + " " + city + " " + street + " " + streetNumber)
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
