package com.takeaway.service;

import com.takeaway.config.ConsumerQueueConfig;
import com.takeaway.domain.QueueDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RabbitMqService {

    private final ConsumerQueueConfig consumerQueueConfig;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqService(ConsumerQueueConfig consumerQueueConfig, RabbitTemplate rabbitTemplate) {
        this.consumerQueueConfig = consumerQueueConfig;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void writeConsumerQueue(QueueDTO dto) {
        rabbitTemplate.convertAndSend(consumerQueueConfig.getExchange(), consumerQueueConfig.getRouting(), dto);
    }
}
