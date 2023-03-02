package com.codecool.TaskTiger.repository;

import com.codecool.TaskTiger.model.user.TaskerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskerInfoRepository extends JpaRepository<TaskerInfo, Long> {
}
