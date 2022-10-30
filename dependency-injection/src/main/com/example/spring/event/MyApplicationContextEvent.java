package com.example.spring.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
* @author liuwenxue
* @date 2022-10-30
*/
public class MyApplicationContextEvent extends ApplicationContextEvent {
    /**
     * Create a new ContextStartedEvent.
     *
     * @param source the {@code ApplicationContext} that the event is raised for
     *               (must not be {@code null})
     */
    public MyApplicationContextEvent(ApplicationContext source) {
        super(source);
    }
}
