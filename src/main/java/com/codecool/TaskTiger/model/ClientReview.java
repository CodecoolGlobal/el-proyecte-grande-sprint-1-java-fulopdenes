package com.codecool.TaskTiger.model;

import com.codecool.TaskTiger.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.ALL;

@Entity(name = "ClientReview")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClientReview {

    @Id
    @SequenceGenerator(name = "clientReview_sequence",
            sequenceName = "clientReview_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "clientReview_sequence")
    @Column(name = "client_review_id", updatable = false)
    private Long id;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "client_user_id", updatable = false, nullable = false)
    private User reviewedUser;

    @Column(name = "review_value", nullable = false)
    private int reviewValue;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

}
