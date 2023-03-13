package com.codecool.TaskTiger.repository;

import com.codecool.TaskTiger.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);

    User getUserByUsernameAndPassword(String userName, String password);




}
