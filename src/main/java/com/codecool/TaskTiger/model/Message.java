package com.codecool.TaskTiger.model;

import com.codecool.TaskTiger.model.user.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Messages")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Message {
    @Id
    @SequenceGenerator(name = "message_sequence",
            sequenceName = "message_sequence")
    @GeneratedValue(strategy = SEQUENCE,
            generator = "message_sequence")
    @Column(name = "message_id", updatable = false)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {REMOVE, MERGE})
    @JoinColumn(name = "reservation_id")
    @JsonBackReference
    @JsonIgnoreProperties({"tasker, client"})
    private Reservation reservation;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "sender_user_id")
    private AppUser sender;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "receiver_user_id")
    private AppUser receiver;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "message", columnDefinition = "TEXT", nullable = false)
    private String message;

}
