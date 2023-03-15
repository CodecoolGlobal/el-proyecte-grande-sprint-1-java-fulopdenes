package com.codecool.TaskTiger.service;

import com.codecool.TaskTiger.dto.ReservationDTO;
import com.codecool.TaskTiger.model.Message;
import com.codecool.TaskTiger.model.Reservation;
import com.codecool.TaskTiger.model.ReservationStatus;
import com.codecool.TaskTiger.model.WorkType;
import com.codecool.TaskTiger.model.user.AppUser;
import com.codecool.TaskTiger.repository.ReservationRepository;
import com.codecool.TaskTiger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }


    public Long persistReservation(ReservationDTO reservationDTO) {
        AppUser client = userRepository.getUserById(reservationDTO.client().longValue());
        AppUser tasker = userRepository.getUserById(reservationDTO.tasker().longValue());
        Message message =
                Message.builder().message(reservationDTO.message()).createdDate(LocalDateTime.now()).sender(client).receiver(tasker).build();
        Reservation savedReservation = Reservation.builder().createdDate(LocalDateTime.now())
                .client(client).tasker(tasker)
                .description(reservationDTO.description()).workType(WorkType.valueOf(reservationDTO.workType()))
                .reservationStatus(ReservationStatus.PENDING).address(reservationDTO.address())
                .messageList(List.of(message)).build();
        savedReservation = reservationRepository.save(savedReservation);
        return savedReservation.getClient().getId();
    }


//
//    public ReservationDTO getReservationById(Long id) {
//        Reservation r = reservationRepository.findById(id).orElseThrow();
//        return new ReservationDTO(r.getCreatedDate(), r.getClient(), r.getTasker(), r.getDescription(),
//                r.getWorkType(), r.getReservationStatus(), r.getAddress(), r.getMessageList());
//    }
//
//    public List<ReservationDTO> getReservationsByClientId(Long id) {
//        return reservationRepository.findAllByClient_Id(id).stream().map(r -> new ReservationDTO(r.getCreatedDate(),
//                r.getClient(), r.getTasker(), r.getDescription(), r.getWorkType(), r.getReservationStatus(),
//                r.getAddress(), r.getMessageList())).toList();
//    }
//
//    public List<ReservationDTO> getReservationsByTaskerId(Long id) {
//        return reservationRepository.findAllByTasker_Id(id).stream().map(r -> new ReservationDTO(r.getCreatedDate(),
//                r.getClient(), r.getTasker(), r.getDescription(), r.getWorkType(), r.getReservationStatus(),
//                r.getAddress(), r.getMessageList())).toList();
//    }
//    public boolean saveMessage(MessageDTO messageDTO, Long id) {
//        Reservation reservation = reservationRepository.findById(id).orElseThrow();
//        reservation.getMessageList().add(Message.builder().sender(messageDTO.sender()).
//                receiver(messageDTO.receiver()).createdDate(messageDTO.createdDate()).
//                message(messageDTO.message()).reservation(messageDTO.reservation()).build());
//        reservationRepository.save(reservation);
//        return true;
//    }
//
//    public boolean modifyReservationStatus(ReservationStatus reservationStatus, Long id){
//        Reservation reservation = reservationRepository.findById(id).orElseThrow();
//        reservation.setReservationStatus(reservationStatus);
//        return true;
//    }
}
