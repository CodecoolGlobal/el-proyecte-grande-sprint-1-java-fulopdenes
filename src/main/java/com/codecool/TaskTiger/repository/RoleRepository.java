package com.codecool.TaskTiger.repository;

import com.codecool.TaskTiger.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
