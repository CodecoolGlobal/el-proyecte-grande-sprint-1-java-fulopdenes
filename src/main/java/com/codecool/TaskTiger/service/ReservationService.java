package com.codecool.TaskTiger.service;

import com.codecool.TaskTiger.dto.MessageDTO;
import com.codecool.TaskTiger.dto.NewReservationDTO;
import com.codecool.TaskTiger.dto.ReservationDTO;
import com.codecool.TaskTiger.dto.StatusDTO;
import com.codecool.TaskTiger.model.Message;
import com.codecool.TaskTiger.model.Reservation;
import com.codecool.TaskTiger.model.ReservationStatus;
import com.codecool.TaskTiger.model.WorkType;
import com.codecool.TaskTiger.model.user.AppUser;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.repository.MessageRepository;
import com.codecool.TaskTiger.repository.ReservationRepository;
import com.codecool.TaskTiger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    private final MessageRepository messageRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
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
        Reservation savedReservation = Reservation.builder().createdDate(LocalDateTime.now())
                .client(client).tasker(taskerInfo)
                .description(newReservationDTO.description())
                .workType(WorkType.valueOf(newReservationDTO.workType()))
                .duration(newReservationDTO.duration())
                .reservationStatus(ReservationStatus.PENDING)
                .address(newReservationDTO.address())
                .build();
        message.setReservation(savedReservation);
        savedReservation.setMessageList(List.of(message));
        savedReservation = reservationRepository.save(savedReservation);
        return savedReservation.getId().intValue();
    }


    public List<MessageDTO> getMessagesByReservation(Long id) {
        List<Message> messages = messageRepository.findAllByReservation_Id(id);
        return messages.stream().map(message -> MessageDTO.builder()
                .reservationId(message.getId().intValue())
                .senderId(message.getSender().getId().intValue())
                .receiverId(message.getReceiver().getId().intValue())
                .message(message.getMessage())
                .build()).collect(Collectors.toList());
    }

    public ReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        return reservationToDTO(reservation);
    }

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
    public boolean saveMessage(MessageDTO messageDTO, Long reservationId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);

        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();

            Message message = convertMessageDtoToEntity(messageDTO);
            message.setReservation(reservation);

            List<Message> messages = reservation.getMessageList();
            messages.add(message);

            reservationRepository.save(reservation);
            return true;
        }

        return false;
    }

    private Message convertMessageDtoToEntity(MessageDTO messageDTO) {
        AppUser sender = userRepository.findById(messageDTO.senderId().longValue())
                .orElseThrow(() -> new NoSuchElementException("Sender not found"));
        AppUser receiver = userRepository.findById(messageDTO.receiverId().longValue())
                .orElseThrow(() -> new NoSuchElementException("Receiver not found"));

        return Message.builder()
                .sender(sender)
                .receiver(receiver)
                .createdDate(messageDTO.createdDate())
                .message(messageDTO.message())
                .build();
    }
//    public boolean modifyReservationStatus(ReservationStatus reservationStatus, Long id){
//        Reservation reservation = reservationRepository.findById(id).orElseThrow();
//        reservation.setReservationStatus(reservationStatus);
//        return true;
//    }


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
