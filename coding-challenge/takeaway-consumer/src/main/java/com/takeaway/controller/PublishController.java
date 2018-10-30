package com.takeaway.controller;

import com.takeaway.domain.QueueDTO;
import com.takeaway.service.RabbitMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
public class PublishController {

    @Autowired
    private RabbitMqService rabbitMqService;

    @PostMapping("publish")
    public ResponseEntity<QueueDTO> publish(@Valid @RequestBody QueueDTO dto) {
        dto.setLocalDateTime(LocalDateTime.now());
        rabbitMqService.writeConsumerQueue(dto);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
