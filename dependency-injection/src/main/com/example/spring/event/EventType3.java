package com.example.spring.event;

import org.springframework.context.PayloadApplicationEvent;

/**
 * @author liuwenxue
 * @date 2022-10-30
 */
public class EventType3 extends PayloadApplicationEvent<String> {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public EventType3(String source) {
        super(source, source);
    }
}
