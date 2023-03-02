package com.codecool.TaskTiger.repository;

import com.codecool.TaskTiger.model.TaskerReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskerReviewRepository extends JpaRepository<TaskerReview, Long> {
}
