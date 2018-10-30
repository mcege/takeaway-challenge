package com.takeaway.controller;

import com.takeaway.domain.QueueDTO;
import com.takeaway.entity.Message;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProducerMapper {

    private ProducerMapper() {
    }

    public static QueueDTO makeQueueDTO(Message message) {
        return QueueDTO.builder()
                .name(message.getName())
                .localDateTime(message.getLocalDateTime())
                .build();
    }

    public static List<QueueDTO> makeQueueDTOList(Collection<Message> messages) {
        return messages.stream()
                .map(ProducerMapper::makeQueueDTO)
                .collect(Collectors.toList());
    }
}
