package com.codecool.TaskTiger.controller;

import com.codecool.TaskTiger.dto.ReservationDTO;
import com.codecool.TaskTiger.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reservation")
public class ReservationController {


    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
//
//    @GetMapping("/all/client/{id}")
//    public List<Reservation> getReservationByClient(@PathVariable Long id) {
//        List<ReservationDTO> r = reservationService.getReservationsByClientId(id);
//        return r.stream().map(reservationDTO -> Reservation.builder().createdDate(reservationDTO.createdDate())
//                .client(reservationDTO.client()).tasker(reservationDTO.tasker())
//                .description(reservationDTO.description()).workType(reservationDTO.workType())
//                .reservationStatus(reservationDTO.reservationStatus()).address(reservationDTO.address())
//                .messageList(reservationDTO.messageList()).build()).toList();
//    }
//
//    @GetMapping("/all/tasker/{id}")
//    public List<Reservation> getReservationByTasker(@PathVariable Long id) {
//        List<ReservationDTO> r = reservationService.getReservationsByTaskerId(id);
//        return r.stream().map(reservationDTO -> Reservation.builder().createdDate(reservationDTO.createdDate())
//                .client(reservationDTO.client()).tasker(reservationDTO.tasker())
//                .description(reservationDTO.description()).workType(reservationDTO.workType())
//                .reservationStatus(reservationDTO.reservationStatus()).address(reservationDTO.address())
//                .messageList(reservationDTO.messageList()).build()).toList();
//    }
//
//    @GetMapping("/{id}")
//    public Reservation getReservationById(@PathVariable Long id) {
//        ReservationDTO reservationDTO = reservationService.getReservationById(id);
//        return Reservation.builder().createdDate(reservationDTO.createdDate())
//                .client(reservationDTO.client()).tasker(reservationDTO.tasker())
//                .description(reservationDTO.description()).workType(reservationDTO.workType())
//                .reservationStatus(reservationDTO.reservationStatus()).address(reservationDTO.address())
//                .messageList(reservationDTO.messageList()).build();
//    }


    @PostMapping
    public Long persistReservation(@RequestBody ReservationDTO reservationDTO) {


        return reservationService.persistReservation(reservationDTO);
    }

//    @PutMapping("/message/{reservationId}")
//    public boolean persistNewMessage(@RequestBody Message message, @PathVariable Long reservationId) {
//        MessageDTO messageDTO =
//                MessageDTO.builder().sender(message.getSender()).receiver(message.getSender())
//                        .createdDate(message.getCreatedDate())
//                        .message(message.getMessage()).reservation(message.getReservation()).build();
//
//        return reservationService.saveMessage(messageDTO, reservationId);
//    }
//
//    @PutMapping("/modify/{reservationId}")
//    public boolean modifyReservationStatus(@RequestBody ReservationStatus reservationStatus, @PathVariable Long reservationId) {
//        return reservationService.modifyReservationStatus(reservationStatus, reservationId);
//    }
}
