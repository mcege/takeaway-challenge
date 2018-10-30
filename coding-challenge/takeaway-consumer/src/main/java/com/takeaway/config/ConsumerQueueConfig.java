package com.takeaway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "queues.consumer")
public class ConsumerQueueConfig {

    private String queue;

    private String deadLetter;

    private String exchange;

    private String routing;

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getDeadLetter() {
        return deadLetter;
    }

    public void setDeadLetter(String deadLetter) {
        this.deadLetter = deadLetter;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRouting() {
        return routing;
    }

    public void setRouting(String routing) {
        this.routing = routing;
    }
}
