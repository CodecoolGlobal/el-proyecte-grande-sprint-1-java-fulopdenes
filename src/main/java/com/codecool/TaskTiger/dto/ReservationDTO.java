package com.codecool.TaskTiger.dto;

import com.codecool.TaskTiger.model.ReservationStatus;
import com.codecool.TaskTiger.model.WorkType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private Long id;

    private Long clientId;
    private Long taskerId;
    private String description;
    private WorkType workType;
    private Double duration;
    private ReservationStatus reservationStatus;
    private String address;
    private List<Long> messageIds;
}
