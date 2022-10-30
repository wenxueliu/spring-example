package com.example.spring.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @author liuwenxue
 * @date 2022-10-30
 */
public class AwareBusiness implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    void handler() {
        publisher.publishEvent(new EventType1("Hello EventType1"));
        publisher.publishEvent(new EventType2("Hello EventType2"));
    }
}
