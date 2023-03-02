package com.codecool.TaskTiger.model;

import com.codecool.TaskTiger.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.ALL;

@Entity(name = "ClientReviews")
@NoArgsConstructor
@AllArgsConstructor
@Data
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
    @JoinColumn(name = "client_user_id")
    private User reviewedUser;

    @Column(name = "review_value")
    private int reviewValue;

    @Column(name = "review_desc")
    private String description;

}
