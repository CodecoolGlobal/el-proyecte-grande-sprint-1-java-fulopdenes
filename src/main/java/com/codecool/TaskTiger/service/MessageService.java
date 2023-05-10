package com.codecool.TaskTiger.service;

import com.codecool.TaskTiger.dto.MessageDTO;
import com.codecool.TaskTiger.model.Message;
import com.codecool.TaskTiger.model.Reservation;
import com.codecool.TaskTiger.model.user.AppUser;
import com.codecool.TaskTiger.repository.MessageRepository;
import com.codecool.TaskTiger.repository.ReservationRepository;
import com.codecool.TaskTiger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final ReservationRepository reservationRepository;

    private final UserRepository userRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository, ReservationRepository reservationRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
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
}
