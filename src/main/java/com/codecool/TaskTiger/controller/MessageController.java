package com.codecool.TaskTiger.controller;

import com.codecool.TaskTiger.dto.MessageDTO;
import com.codecool.TaskTiger.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{reservationId}")
    public List<MessageDTO> getMessageByResId(@PathVariable Integer reservationId) {
        return messageService.getMessagesByReservation(reservationId.longValue());
    }

    @PutMapping("/{reservationId}")
    public boolean persistNewMessage(@RequestBody MessageDTO messageDTO, @PathVariable Integer reservationId) {
        return messageService.saveMessage(messageDTO, reservationId.longValue());
    }
}
