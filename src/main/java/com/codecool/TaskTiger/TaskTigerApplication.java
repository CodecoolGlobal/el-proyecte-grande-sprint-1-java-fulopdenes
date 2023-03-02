package com.codecool.TaskTiger;

import com.codecool.TaskTiger.model.*;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.model.user.User;
import com.codecool.TaskTiger.repository.ReservationRepository;
import com.codecool.TaskTiger.repository.TimeSlotRepository;
import com.codecool.TaskTiger.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.crypto.Data;
import java.util.List;


@SpringBootApplication
public class TaskTigerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskTigerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner2(UserRepository userRepository, DataGenerator dataGenerator,
                                         TimeSlotRepository timeSlotRepository, ReservationRepository reservationRepository) {
        List<WorkType> feriTypes = List.of(WorkType.CLEANING, WorkType.GARDENING);
        List<WorkType> mariTypes = List.of(WorkType.DOG_WALKING, WorkType.GARDENING);
        List<WorkType> tecaTypes = List.of(WorkType.FURNITURE_ASSEMBLY, WorkType.MINOR_HOME_REPAIRS);
        List<WorkType> gyuriTypes = List.of(WorkType.CLEANING, WorkType.DOG_WALKING);
        List<WorkType> siyarTypes = List.of(WorkType.MINOR_HOME_REPAIRS, WorkType.HELP_MOVING, WorkType.CLEANING);

        return (args) -> {
//

            User user = DataGenerator.generateRandomUser("mari123", "Mária", "Kovács");
            User savedUser = userRepository.save(user);

            TaskerInfo taskerInfo = TaskerInfo.builder()
                    .user(savedUser).skills(feriTypes)
                    .build();
            savedUser.setTaskerInfo(taskerInfo);
            userRepository.save(savedUser);
            User taskerUser = userRepository.getUserById(savedUser.getId());
            for (int i = 0; i < 30; i++) {
                TimeSlot timeSlot = DataGenerator.generateRandomTimeSlot(taskerUser);
                timeSlot.setTasker(taskerUser.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }

            // Second user
            User user2 = DataGenerator.generateRandomUser("julia89", "Júlia", "Nagy");
            User savedUser2 = userRepository.save(user2);
            TaskerInfo taskerInfo2 = TaskerInfo.builder()
                    .user(savedUser2)
                    .skills(mariTypes)
                    .build();
            savedUser2.setTaskerInfo(taskerInfo2);
            userRepository.save(savedUser2);
            User taskerUser1 = userRepository.getUserById(savedUser2.getId());
            for (int i = 0; i < 30; i++) {
                TimeSlot timeSlot = DataGenerator.generateRandomTimeSlot(taskerUser1);
                timeSlot.setTasker(taskerUser1.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }
// Third user
            User user3 = DataGenerator.generateRandomUser("david00", "Dávid", "Tóth");
            User savedUser3 = userRepository.save(user3);
            TaskerInfo taskerInfo3 = TaskerInfo.builder()
                    .user(savedUser3)
                    .skills(feriTypes)
                    .build();
            savedUser3.setTaskerInfo(taskerInfo3);
            userRepository.save(savedUser3);
            User taskerUser2 = userRepository.getUserById(savedUser3.getId());
            for (int i = 0; i < 30; i++) {
                TimeSlot timeSlot = DataGenerator.generateRandomTimeSlot(taskerUser2);
                timeSlot.setTasker(taskerUser2.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }

// Fourth user
            User user4 = DataGenerator.generateRandomUser("zsuzsa32", "Zsuzsanna", "Balogh");
            User savedUser4 = userRepository.save(user4);
            TaskerInfo taskerInfo4 = TaskerInfo.builder()
                    .user(savedUser4)
                    .skills(tecaTypes)
                    .build();
            savedUser4.setTaskerInfo(taskerInfo4);
            userRepository.save(savedUser4);
            User taskerUser3 = userRepository.getUserById(savedUser4.getId());
            for (int i = 0; i < 30; i++) {
                TimeSlot timeSlot = DataGenerator.generateRandomTimeSlot(taskerUser3);
                timeSlot.setTasker(taskerUser3.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }
// Fifth user
            User user5 = DataGenerator.generateRandomUser("krisztian77", "Krisztián", "Varga");
            User savedUser5 = userRepository.save(user5);
            TaskerInfo taskerInfo5 = TaskerInfo.builder()
                    .user(savedUser5)
                    .skills(gyuriTypes)
                    .build();
            savedUser5.setTaskerInfo(taskerInfo5);
            userRepository.save(savedUser5);
            User taskerUser4 = userRepository.getUserById(savedUser5.getId());
            for (int i = 0; i < 30; i++) {
                TimeSlot timeSlot = DataGenerator.generateRandomTimeSlot(taskerUser4);
                timeSlot.setTasker(taskerUser4.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }


            User mari = DataGenerator.generateRandomUser("dénes5567", "Dénes", "Fülöp");
            User mariSaved = userRepository.save(mari);
            TaskerInfo mariTaskerInfo = TaskerInfo.builder()
                    .user(mariSaved)
                    .skills(mariTypes)
                    .build();
            mariSaved.setTaskerInfo(mariTaskerInfo);
            userRepository.save(mariSaved);

            User taskerUser5 = userRepository.getUserById(mariSaved.getId());
            for (int i = 0; i < 30; i++) {
                TimeSlot timeSlot = DataGenerator.generateRandomTimeSlot(taskerUser5);
                timeSlot.setTasker(taskerUser5.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }
            User teca = DataGenerator.generateRandomUser("teca123", "Teca", "Kiss");
            User tecaSaved = userRepository.save(teca);
            TaskerInfo tecaTaskerInfo = TaskerInfo.builder()
                    .user(tecaSaved)
                    .skills(tecaTypes)
                    .build();
            tecaSaved.setTaskerInfo(tecaTaskerInfo);
            userRepository.save(tecaSaved);
            User taskerUser6 = userRepository.getUserById(tecaSaved.getId());
            for (int i = 0; i < 30; i++) {
                TimeSlot timeSlot = DataGenerator.generateRandomTimeSlot(taskerUser6);
                timeSlot.setTasker(taskerUser6.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }
            User gyuri = DataGenerator.generateRandomUser("gyuri123", "Gyuri", "Kovács");
            User gyuriSaved = userRepository.save(gyuri);
            TaskerInfo gyuriTaskerInfo = TaskerInfo.builder()
                    .user(gyuriSaved)
                    .skills(gyuriTypes)
                    .build();
            gyuriSaved.setTaskerInfo(gyuriTaskerInfo);
            userRepository.save(gyuriSaved);
            User taskerUser7 = userRepository.getUserById(gyuriSaved.getId());
            for (int i = 0; i < 30; i++) {
                TimeSlot timeSlot = DataGenerator.generateRandomTimeSlot(taskerUser7);
                timeSlot.setTasker(taskerUser7.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }
            User siyar = DataGenerator.generateRandomUser("siyar123", "Siyar", "Ahmad");
            User siyarSaved = userRepository.save(siyar);
            TaskerInfo siyarTaskerInfo = TaskerInfo.builder()
                    .user(siyarSaved)
                    .skills(siyarTypes)
                    .build();
            siyarSaved.setTaskerInfo(siyarTaskerInfo);
            userRepository.save(siyarSaved);
            User taskerUser8 = userRepository.getUserById(siyarSaved.getId());
            for (int i = 0; i < 30; i++) {
                TimeSlot timeSlot = DataGenerator.generateRandomTimeSlot(taskerUser8);
                timeSlot.setTasker(taskerUser8.getTaskerInfo());
                timeSlotRepository.save(timeSlot);
            }
//            User zsolt = DataGenerator.generateRandomUser("zsolti", "Zsolt", "Béka");
//            User savedZsolt = userRepository.save(zsolt); // save the entity and get the saved object with generated id
//            Address address = DataGenerator.generateRandomAddress();
//            savedZsolt.setTaskerInfo(null);
//            savedZsolt.setTasker(false);
//            userRepository.save(savedZsolt);
//            Reservation reservation = DataGenerator.generateRandomReservation(savedZsolt, siyar, address, WorkType.HELP_MOVING);
//            reservationRepository.save(reservation);
        };


    }

};


