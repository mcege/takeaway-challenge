package com.takeaway.controller;

import com.takeaway.domain.QueueDTO;
import com.takeaway.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubscribeController {

    @Autowired
    private MessageService messageService;

    @GetMapping("subscribe")
    @ResponseStatus(HttpStatus.OK)
    public List<QueueDTO> listMessages() {
        return messageService.getMessages();
    }
}
