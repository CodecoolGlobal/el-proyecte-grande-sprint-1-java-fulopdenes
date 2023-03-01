package com.codecool.TaskTiger.service;

import com.codecool.TaskTiger.repository.TaskerReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskerReviewService {
    private final TaskerReviewRepository taskerReviewRepository;

    @Autowired
    public TaskerReviewService(TaskerReviewRepository taskerReviewRepository) {
        this.taskerReviewRepository = taskerReviewRepository;
    }
}
