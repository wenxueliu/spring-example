package com.example.spring.event;

import org.springframework.context.ApplicationEvent;

/**
* @author liuwenxue
* @date 2022-10-30
*/
public class EventType1 extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public EventType1(String source) {
        super(source);
    }
}
