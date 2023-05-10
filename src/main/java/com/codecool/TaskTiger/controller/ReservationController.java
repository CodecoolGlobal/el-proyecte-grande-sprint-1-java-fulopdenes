package com.codecool.TaskTiger.controller;

import com.codecool.TaskTiger.dto.NewReservationDTO;
import com.codecool.TaskTiger.dto.ReservationDTO;
import com.codecool.TaskTiger.dto.StatusDTO;
import com.codecool.TaskTiger.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @PutMapping("/modify/{reservationId}")
    public boolean modifyReservationStatus(@RequestBody StatusDTO reservationStatus,
                                           @PathVariable Integer reservationId) {
        return reservationService.modifyReservationStatus(reservationId, reservationStatus);
    }
}
