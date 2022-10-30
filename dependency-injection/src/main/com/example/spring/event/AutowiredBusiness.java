package com.example.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @author liuwenxue
 * @date 2022-10-30
 */
public class AutowiredBusiness {
    @Autowired
    private ApplicationEventPublisher publisher;

    void handler() {
        publisher.publishEvent(new EventType1("Hello EventType1"));
        publisher.publishEvent(new EventType2("Hello EventType2"));
    }
}
