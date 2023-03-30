package com.codecool.TaskTiger.controller;

import com.codecool.TaskTiger.dto.ReviewDTO;
import com.codecool.TaskTiger.model.TaskerReview;
import com.codecool.TaskTiger.service.ReviewService;
import com.codecool.TaskTiger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public boolean sendReview(@RequestBody ReviewDTO review) {
        return reviewService.sendReview(review);
    }

    @GetMapping("/{reservationId}")
    public List<TaskerReview> getReviewByReservation(@PathVariable Integer reservationId) {
        return reviewService.getReviewByReservation(reservationId);
    }
}
