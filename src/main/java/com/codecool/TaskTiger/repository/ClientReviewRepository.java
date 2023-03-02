package com.codecool.TaskTiger.repository;

import com.codecool.TaskTiger.model.ClientReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientReviewRepository extends JpaRepository<ClientReview, Long> {
}
