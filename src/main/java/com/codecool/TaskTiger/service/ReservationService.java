package com.codecool.TaskTiger.service;

import com.codecool.TaskTiger.dto.NewReservationDTO;
import com.codecool.TaskTiger.dto.ReservationDTO;
import com.codecool.TaskTiger.dto.StatusDTO;
import com.codecool.TaskTiger.model.Message;
import com.codecool.TaskTiger.model.Reservation;
import com.codecool.TaskTiger.model.ReservationStatus;
import com.codecool.TaskTiger.model.WorkType;
import com.codecool.TaskTiger.model.user.AppUser;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.repository.ReservationRepository;
import com.codecool.TaskTiger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    public boolean modifyReservationStatus(Integer reservationId, StatusDTO status){
        Reservation reservation = reservationRepository.findById(reservationId.longValue()).orElseThrow();
        ReservationStatus reservationStatus = ReservationStatus.valueOf(status.reservationStatus().toUpperCase());
        reservation.setReservationStatus(reservationStatus);
        reservationRepository.save(reservation);
        return true;
    }

    public Integer persistReservation(NewReservationDTO newReservationDTO) {
        AppUser client = userRepository.getUserById(newReservationDTO.client().longValue());
        AppUser tasker = userRepository.getUserById(newReservationDTO.tasker().longValue());
        TaskerInfo taskerInfo = tasker.getTaskerInfo();

        Message message =
                Message.builder().message(newReservationDTO.message()).createdDate(LocalDateTime.now()).sender(client).receiver(tasker).build();

        Reservation savedReservation = Reservation.builder()
                .createdDate(LocalDateTime.now())
                .client(client).tasker(taskerInfo)
                .description(newReservationDTO.description())
                .workType(WorkType.valueOf(newReservationDTO.workType()))
                .duration(newReservationDTO.duration())
                .reservationStatus(ReservationStatus.PENDING)
                .address(newReservationDTO.address())
                .build();

        message.setReservation(savedReservation);

        savedReservation.setMessageList(List.of(message));
        savedReservation = reservationRepository.saveAndFlush(savedReservation);
        return savedReservation.getId().intValue();
    }

    public ReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        return reservationToDTO(reservation);
    }

    public ReservationDTO reservationToDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setClientId(reservation.getClient().getId());
        reservationDTO.setTaskerId(reservation.getTasker().getId());
        reservationDTO.setDescription(reservation.getDescription());
        reservationDTO.setWorkType(reservation.getWorkType());
        reservationDTO.setDuration(reservation.getDuration());
        reservationDTO.setReservationStatus(reservation.getReservationStatus());
        reservationDTO.setAddress(reservation.getAddress());
        reservationDTO.setMessageIds(
                reservation.getMessageList().stream()
                        .map(Message::getId)
                        .collect(Collectors.toList())
        );
        return reservationDTO;
    }
}
