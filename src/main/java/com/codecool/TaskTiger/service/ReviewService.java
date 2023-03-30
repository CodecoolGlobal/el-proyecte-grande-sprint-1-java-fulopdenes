package com.codecool.TaskTiger.service;

import com.codecool.TaskTiger.dto.ReviewDTO;
import com.codecool.TaskTiger.model.Reservation;
import com.codecool.TaskTiger.model.TaskerReview;
import com.codecool.TaskTiger.model.WorkType;
import com.codecool.TaskTiger.model.user.AppUser;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.repository.ReservationRepository;
import com.codecool.TaskTiger.repository.TaskerReviewRepository;
import com.codecool.TaskTiger.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    private final TaskerReviewRepository taskerReviewRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public ReviewService(TaskerReviewRepository taskerReviewRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.taskerReviewRepository = taskerReviewRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }

    public boolean sendReview(ReviewDTO review) {
        TaskerInfo reviewed = userRepository.getUserById(review.reviewed().longValue()).getTaskerInfo();
        AppUser reviewer = userRepository.getUserById(review.reviewer().longValue());
        Reservation reservation = reservationRepository.findById(review.reservationId().longValue()).orElseThrow();

        TaskerReview newReview = TaskerReview.builder()
                .reviewed(reviewed)
                .reviewer(reviewer)
                .reviewValue(review.reviewValue())
                .workType(WorkType.valueOf(review.workType()))
                .description(review.description())
                .createdDate(LocalDateTime.now())
                .reservation(reservation)
                .build();

        newReview = taskerReviewRepository.save(newReview);
        return true;
    }

    public List<TaskerReview> getReviewByReservation(Integer reservationId) {
        return taskerReviewRepository.findAllByReservation_Id(reservationId.longValue());
    }
}
