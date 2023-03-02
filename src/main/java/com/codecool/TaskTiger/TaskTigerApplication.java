package com.codecool.TaskTiger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskTigerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskTigerApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner2(UserRepository userRepository) {
//        return (args) -> {
//            // save a few users
//            User user1 = new User(1L,"username1", "firstname1", "lastName1", LocalDate.now(),
//                    Gender.FEMALE,
//                    null, "emailAddress", "123", true, true, "password", LocalDateTime.now(), new ArrayList<>(),
//                    new ArrayList<>(), Role.builder().build(), null, LocalDateTime.now(), false);
//            userRepository.save(user1);
//        };
//    }
}
