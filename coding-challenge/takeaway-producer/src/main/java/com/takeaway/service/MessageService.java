package com.takeaway.service;

import com.takeaway.controller.ProducerMapper;
import com.takeaway.domain.QueueDTO;
import com.takeaway.entity.Message;
import com.takeaway.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<QueueDTO> getMessages() {
        List<Message> messagesList = findMessages();

        return ProducerMapper.makeQueueDTOList(messagesList);
    }

    public List<Message> findMessages() {
        return messageRepository.findAll(Sort.by(Sort.Direction.DESC, "localDateTime"));
    }

}
