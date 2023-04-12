package com.codecool.TaskTiger.controller;

import com.codecool.TaskTiger.dto.MessageDTO;
import com.codecool.TaskTiger.dto.NewReservationDTO;
import com.codecool.TaskTiger.dto.ReservationDTO;
import com.codecool.TaskTiger.dto.StatusDTO;
import com.codecool.TaskTiger.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public ReservationDTO getReservationById(@PathVariable Integer id) {
        return reservationService.getReservationById(id.longValue());
    }

    @PostMapping
    public Integer persistReservation(@RequestBody NewReservationDTO newReservationDTO) {
        return reservationService.persistReservation(newReservationDTO);
    }
    // TODO: suggest also to create a messageController.
    @GetMapping("/message/{reservationId}")
    public List<MessageDTO> getMessageByResId(@PathVariable Integer reservationId) {
        return reservationService.getMessagesByReservation(reservationId.longValue());
    }

    @PutMapping("/message/{reservationId}")
    public boolean persistNewMessage(@RequestBody MessageDTO messageDTO, @PathVariable Integer reservationId) {
        return reservationService.saveMessage(messageDTO, reservationId.longValue());
    }

    @PutMapping("/modify/{reservationId}")
    public boolean modifyReservationStatus(@RequestBody StatusDTO reservationStatus,
                                           @PathVariable Integer reservationId) {
        return reservationService.modifyReservationStatus(reservationId, reservationStatus);
    }
}
