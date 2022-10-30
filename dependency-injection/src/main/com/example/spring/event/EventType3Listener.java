package com.example.spring.event;

import org.springframework.context.ApplicationListener;

/**
 * @author liuwenxue
 * @date 2022-10-30
 */
public class EventType3Listener implements ApplicationListener<EventType3> {
    @Override
    public void onApplicationEvent(EventType3 event) {
        System.out.printf("[thread=%s] onApplicationEvent: %s\n", Thread.currentThread().getName(), event);
    }
}
