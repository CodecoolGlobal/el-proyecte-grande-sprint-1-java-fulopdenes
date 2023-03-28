package com.codecool.TaskTiger.model;

import com.codecool.TaskTiger.model.user.AppUser;
import com.codecool.TaskTiger.model.user.Gender;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Component
public class DataGenerator {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Random RANDOM = new Random();

    public AppUser generateRandomUser(String userName, String firstName, String lastName) {
        return AppUser.builder()
                .username(userName)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(generateRandomPhoneNumber())
                .isActivated(true)
                .dob(generateRandomLocalDate(1980, 2002))
                .email(generateRandomString(8) + "@example.com")
                .isTasker(true)
                .password(passwordEncoder.encode("password"))
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

    public String time(LocalDateTime localDateTime) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d:%02d", localDateTime.getHour(), localDateTime.getMinute()));
        return sb.toString();
    }

    public TimeSlot generateRandomTimeSlot(AppUser appUser) {
        LocalDateTime startTime =
                LocalDateTime.of(2023, 3, 13, 10, 0).plusDays(RANDOM.nextInt(7)).plusHours(RANDOM.nextInt(24));
        LocalDateTime endTime = startTime.plusHours(1);
        boolean isReserved = RANDOM.nextBoolean();

        String lable = time(startTime) + "-" + time(endTime);

        String backColor = isReserved ? "" : "#6aa84f";
        return TimeSlot.builder()
                .start(startTime)
                .end(endTime)
                .isReserved(isReserved)
                .backColor(backColor)
                .text(lable)
                .tasker(appUser.getTaskerInfo())
                .build();
    }

    private final String[] COUNTRIES = {"Hungary", "USA", "Germany", "France", "UK"};
    private final String[] ZIPCODES = {"1011", "2000", "1234", "5678", "9999"};
    private final String[] COUNTIES = {"Budapest", "New York", "Berlin", "Paris", "London"};
    private final String[] CITIES = {"Budapest", "New York", "Berlin", "Paris", "London"};
    private final String[] STREETS = {"Main Street", "Broadway", "Fifth Avenue", "Champs-Élysées", "Oxford Street"};


    public Address generateRandomAddress() {
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


    public Reservation generateRandomReservation(AppUser client, TaskerInfo tasker, Address address, WorkType workType) {
        LocalDateTime createdDate = LocalDateTime.now();
        String description = "Random description";
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
                .address(country + " " + zipcode + " " + county + " " + city + " " + street + " " + streetNumber)
                .build();
    }

    private String generateRandomPhoneNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }

    private LocalDate generateRandomLocalDate(int startYear, int endYear) {
        int year = startYear + RANDOM.nextInt(endYear - startYear + 1);
        int month = 1 + RANDOM.nextInt(12);
        int day = 1 + RANDOM.nextInt(28);
        return LocalDate.of(year, month, day);
    }
}
