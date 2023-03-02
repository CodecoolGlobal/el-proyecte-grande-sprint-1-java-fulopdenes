package com.codecool.TaskTiger.service;

import com.codecool.TaskTiger.dto.MessageDTO;
import com.codecool.TaskTiger.dto.ReservationDTO;
import com.codecool.TaskTiger.model.Message;
import com.codecool.TaskTiger.model.Reservation;
import com.codecool.TaskTiger.model.ReservationStatus;
import com.codecool.TaskTiger.repository.ReservationRepository;
import com.codecool.TaskTiger.repository.TimeSlotRepository;
import com.codecool.TaskTiger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    private final TimeSlotRepository timeSlotRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, TimeSlotRepository timeSlotRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.timeSlotRepository = timeSlotRepository;
    }


    public Long persistReservation(ReservationDTO reservationDTO) {

        Reservation savedReservation =
                reservationRepository.save(Reservation.builder().createdDate(reservationDTO.createdDate())
                        .client(reservationDTO.client()).tasker(reservationDTO.tasker())
                        .description(reservationDTO.description()).workType(reservationDTO.workType())
                        .reservationStatus(reservationDTO.reservationStatus()).address(reservationDTO.address())
                        .messageList(reservationDTO.messageList()).build());
        return savedReservation.getClient().getId();
    }




    public ReservationDTO getReservationById(Long id) {
        Reservation r = reservationRepository.findById(id).orElseThrow();
        return new ReservationDTO(r.getCreatedDate(), r.getClient(), r.getTasker(), r.getDescription(),
                r.getWorkType(), r.getReservationStatus(), r.getAddress(), r.getMessageList(),
                timeSlotRepository.findAllByReservation_Id(id));
    }

    public List<ReservationDTO> getReservationsByClientId(Long id) {
        return reservationRepository.findAllByClient_Id(id).stream().map(r -> new ReservationDTO(r.getCreatedDate(),
                r.getClient(), r.getTasker(), r.getDescription(), r.getWorkType(), r.getReservationStatus(),
                r.getAddress(), r.getMessageList(), timeSlotRepository.findAllByReservation_Id(r.getId()))).toList();
    }

    public List<ReservationDTO> getReservationsByTaskerId(Long id) {
        return reservationRepository.findAllByTasker_Id(id).stream().map(r -> new ReservationDTO(r.getCreatedDate(),
                r.getClient(), r.getTasker(), r.getDescription(), r.getWorkType(), r.getReservationStatus(),
                r.getAddress(), r.getMessageList(), timeSlotRepository.findAllByReservation_Id(r.getId()))).toList();
    }
    public boolean saveMessage(MessageDTO messageDTO, Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        reservation.getMessageList().add(Message.builder().sender(messageDTO.sender()).
                receiver(messageDTO.reciever()).createdDate(messageDTO.createdDate()).
                message(messageDTO.message()).reservation(messageDTO.reservation()).build());
        reservationRepository.save(reservation);
        return true;
    }

    public boolean modifyReservationStatus(ReservationStatus reservationStatus, Long id){
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        reservation.setReservationStatus(reservationStatus);
        return true;
    }
}
