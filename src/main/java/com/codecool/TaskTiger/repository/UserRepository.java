package com.codecool.TaskTiger.repository;

import com.codecool.TaskTiger.model.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser getUserById(Long id);

    Optional<AppUser> getUserByUsername(String username);




}
