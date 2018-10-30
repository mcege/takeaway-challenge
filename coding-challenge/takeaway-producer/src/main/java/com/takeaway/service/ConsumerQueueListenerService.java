package com.takeaway.service;

import com.takeaway.domain.QueueDTO;
import com.takeaway.entity.Message;
import com.takeaway.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ConsumerQueueListenerService {

    @Autowired
    private MessageRepository messageRepository;

    @RabbitListener(queues = "${queues.consumer.queue}")
    public void handleMessage(QueueDTO payload) {

        Message message = new Message();
        message.setName(payload.getName());
        message.setLocalDateTime(payload.getLocalDateTime());

        messageRepository.save(message);
        log.info("Payload saved {}", message);
    }

}
