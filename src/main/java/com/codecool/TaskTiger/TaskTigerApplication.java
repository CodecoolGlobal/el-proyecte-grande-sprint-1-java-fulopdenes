package com.codecool.TaskTiger;

import com.codecool.TaskTiger.model.*;
import com.codecool.TaskTiger.model.user.AppUser;
import com.codecool.TaskTiger.model.user.Role;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.repository.ReservationRepository;
import com.codecool.TaskTiger.repository.RoleRepository;
import com.codecool.TaskTiger.repository.TimeSlotRepository;
import com.codecool.TaskTiger.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class TaskTigerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskTigerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner2(UserRepository userRepository, RoleRepository roleRepository,
                                         TimeSlotRepository timeSlotRepository,
                                         ReservationRepository reservationRepository, DataGenerator dataGenerator) {
        List<WorkType> feriTypes = List.of(WorkType.CLEANING, WorkType.GARDENING);
        List<WorkType> mariTypes = List.of(WorkType.DOG_WALKING, WorkType.GARDENING);
        List<WorkType> tecaTypes = List.of(WorkType.FURNITURE_ASSEMBLY, WorkType.MINOR_HOME_REPAIRS);
        List<WorkType> gyuriTypes = List.of(WorkType.CLEANING, WorkType.DOG_WALKING);
        List<WorkType> siyarTypes = List.of(WorkType.MINOR_HOME_REPAIRS, WorkType.HELP_MOVING, WorkType.CLEANING);

        return (args) -> {
            Role user = Role.builder().name(Roles.ROLE_USER).build();

            Role admin = Role.builder().name(Roles.ROLE_ADMIN).build();

            Role userRole = roleRepository.save(user);
            Role adminRole = roleRepository.save(admin);


            AppUser appUser = dataGenerator.generateRandomUser("mari123", "Mária", "Kovács");
            AppUser savedAppUser = userRepository.save(appUser);
            appUser.setRole(userRole);
            TaskerInfo taskerInfo = TaskerInfo.builder()
                    .skills(feriTypes).hourlyWage(10.0)
                    .build();
            savedAppUser.setTaskerInfo(taskerInfo);
            userRepository.save(savedAppUser);
            AppUser taskerAppUser = userRepository.getUserById(savedAppUser.getId());
            for (int i = 0; i < 60; i++) {
                TimeSlot timeSlot = dataGenerator.generateRandomTimeSlot(taskerAppUser);
                timeSlot.setTasker(taskerAppUser.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }

            // Second user
            AppUser appUser2 = dataGenerator.generateRandomUser("julia89", "Júlia", "Nagy");
            AppUser savedAppUser2 = userRepository.save(appUser2);
            appUser2.setRole(userRole);
            TaskerInfo taskerInfo2 = TaskerInfo.builder()
                    .skills(mariTypes).hourlyWage(10.0)
                    .build();
            savedAppUser2.setTaskerInfo(taskerInfo2);
            userRepository.save(savedAppUser2);
            AppUser taskerAppUser1 = userRepository.getUserById(savedAppUser2.getId());
            for (int i = 0; i < 30; i++) {
                TimeSlot timeSlot = dataGenerator.generateRandomTimeSlot(taskerAppUser1);
                timeSlot.setTasker(taskerAppUser1.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }
// Third user
            AppUser appUser3 = dataGenerator.generateRandomUser("david00", "Dávid", "Tóth");
            AppUser savedAppUser3 = userRepository.save(appUser3);
            appUser3.setRole(userRole);
            TaskerInfo taskerInfo3 = TaskerInfo.builder()
                    .skills(feriTypes).hourlyWage(10.0)
                    .build();
            savedAppUser3.setTaskerInfo(taskerInfo3);
            userRepository.save(savedAppUser3);
            AppUser taskerAppUser2 = userRepository.getUserById(savedAppUser3.getId());
            for (int i = 0; i < 30; i++) {
                TimeSlot timeSlot = dataGenerator.generateRandomTimeSlot(taskerAppUser2);
                timeSlot.setTasker(taskerAppUser2.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }

// Fourth user
            AppUser appUser4 = dataGenerator.generateRandomUser("zsuzsa32", "Zsuzsanna", "Balogh");
            AppUser savedAppUser4 = userRepository.save(appUser4);
            appUser4.setRole(userRole);
            TaskerInfo taskerInfo4 = TaskerInfo.builder()
                    .skills(tecaTypes).hourlyWage(12.0)
                    .build();
            savedAppUser4.setTaskerInfo(taskerInfo4);
            userRepository.save(savedAppUser4);
            AppUser taskerAppUser3 = userRepository.getUserById(savedAppUser4.getId());
            for (int i = 0; i < 30; i++) {
                TimeSlot timeSlot = dataGenerator.generateRandomTimeSlot(taskerAppUser3);
                timeSlot.setTasker(taskerAppUser3.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }
// Fifth user
            AppUser appUser5 = dataGenerator.generateRandomUser("krisztian77", "Krisztián", "Varga");
            AppUser savedAppUser5 = userRepository.save(appUser5);
            appUser5.setRole(userRole);
            TaskerInfo taskerInfo5 = TaskerInfo.builder()
                    .skills(gyuriTypes).hourlyWage(17.0)
                    .build();
            savedAppUser5.setTaskerInfo(taskerInfo5);
            userRepository.save(savedAppUser5);
            AppUser taskerAppUser4 = userRepository.getUserById(savedAppUser5.getId());
            for (int i = 0; i < 30; i++) {
                TimeSlot timeSlot = dataGenerator.generateRandomTimeSlot(taskerAppUser4);
                timeSlot.setTasker(taskerAppUser4.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }


            AppUser mari = dataGenerator.generateRandomUser("dénes5567", "Dénes", "Fülöp");
            AppUser mariSaved = userRepository.save(mari);
            mari.setRole(userRole);
            TaskerInfo mariTaskerInfo = TaskerInfo.builder()
                    .skills(mariTypes).hourlyWage(22.0)
                    .build();
            mariSaved.setTaskerInfo(mariTaskerInfo);
            userRepository.save(mariSaved);

            AppUser taskerAppUser5 = userRepository.getUserById(mariSaved.getId());
            for (int i = 0; i < 30; i++) {
                TimeSlot timeSlot = dataGenerator.generateRandomTimeSlot(taskerAppUser5);
                timeSlot.setTasker(taskerAppUser5.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }
            AppUser teca = dataGenerator.generateRandomUser("teca123", "Teca", "Kiss");
            AppUser tecaSaved = userRepository.save(teca);
            teca.setRole(userRole);
            TaskerInfo tecaTaskerInfo = TaskerInfo.builder()
                    .skills(tecaTypes).hourlyWage(11.0)
                    .build();
            tecaSaved.setTaskerInfo(tecaTaskerInfo);
            userRepository.save(tecaSaved);
            AppUser taskerAppUser6 = userRepository.getUserById(tecaSaved.getId());
            for (int i = 0; i < 30; i++) {
                TimeSlot timeSlot = dataGenerator.generateRandomTimeSlot(taskerAppUser6);
                timeSlot.setTasker(taskerAppUser6.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }
            AppUser gyuri = dataGenerator.generateRandomUser("gyuri123", "Gyuri", "Kovács");
            AppUser gyuriSaved = userRepository.save(gyuri);
            gyuri.setRole(userRole);
            TaskerInfo gyuriTaskerInfo = TaskerInfo.builder()
                    .skills(gyuriTypes).hourlyWage(15.0)
                    .build();
            gyuriSaved.setTaskerInfo(gyuriTaskerInfo);
            userRepository.save(gyuriSaved);
            AppUser taskerAppUser7 = userRepository.getUserById(gyuriSaved.getId());
            for (int i = 0; i < 30; i++) {
                TimeSlot timeSlot = dataGenerator.generateRandomTimeSlot(taskerAppUser7);
                timeSlot.setTasker(taskerAppUser7.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }
            AppUser siyar = dataGenerator.generateRandomUser("siyar123", "Siyar", "Ahmad");
            siyar.setRole(userRole);
            AppUser siyarSaved = userRepository.save(siyar);
            TaskerInfo siyarTaskerInfo = TaskerInfo.builder()
                    .skills(siyarTypes).hourlyWage(20.0)
                    .build();
            siyarSaved.setTaskerInfo(siyarTaskerInfo);
            userRepository.save(siyarSaved);

            AppUser taskerAppUser8 = userRepository.getUserById(siyarSaved.getId());
            for (int i = 0; i < 30; i++) {
                TimeSlot timeSlot = dataGenerator.generateRandomTimeSlot(taskerAppUser8);
                timeSlot.setTasker(taskerAppUser8.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }
            AppUser zsolt = dataGenerator.generateRandomUser("zsolti", "Zsolt", "Béka");
            zsolt.setTasker(false);
            zsolt.setRole(adminRole);
            AppUser savedZsolt = userRepository.save(zsolt);
            Address address = dataGenerator.generateRandomAddress();
            Reservation reservation = dataGenerator.generateRandomReservation(savedZsolt, siyar, address,
                    WorkType.HELP_MOVING);
            reservationRepository.save(reservation);
        };


    }

}


