package com.takeaway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    private final ConsumerQueueConfig consumerQueueConfig;

    private final ObjectMapper objectMapper;

    public RabbitMqConfiguration(ConsumerQueueConfig consumerQueueConfig, ObjectMapper objectMapper) {
        this.consumerQueueConfig = consumerQueueConfig;
        this.objectMapper = objectMapper;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Queue consumerQueue() {
        return QueueBuilder.durable(consumerQueueConfig.getQueue())
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", consumerQueueConfig.getDeadLetter())
                .build();
    }

    @Bean
    public Queue consumerDeadLetterQueue() {
        return QueueBuilder.durable(consumerQueueConfig.getDeadLetter()).build();
    }

    @Bean
    public TopicExchange consumerQueueTopicExchange() {
        return new TopicExchange(consumerQueueConfig.getExchange());
    }

    @Bean
    public Binding consumerBinding() {
        return BindingBuilder.bind(consumerQueue()).to(consumerQueueTopicExchange()).with(consumerQueueConfig.getRouting());
    }

    @Bean
    public Binding bindingDeadLetter(Queue consumerDeadLetterQueue, TopicExchange ordersQueueTopicExchange) {
        return BindingBuilder.bind(consumerDeadLetterQueue).to(ordersQueueTopicExchange).with(consumerQueueConfig.getDeadLetter());
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
