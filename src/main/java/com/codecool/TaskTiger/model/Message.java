package com.codecool.TaskTiger.model;

import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Message")
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
    private Long id;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "sender_id", nullable = false, updatable = false)
    private User sender;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "receiver_id", nullable = false, updatable = false)
    private User receiver;
    @Column(name = "message_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @Column(name = "message_text", columnDefinition = "TEXT")
    private String message;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

}
