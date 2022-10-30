package com.example.spring.event;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @author liuwenxue
 * @date 2022-10-30
 */
public class EventType2Listener {
    @EventListener
    @Async
    public void asyncListener(EventType2 event) {
        System.out.printf("[thread=%s] asyncListener : %s\n", Thread.currentThread().getName(), event);
    }

    @EventListener
    @Order(3)
    public void syncListener1(EventType2 event) {
        EventUtils.print(event, "syncListener1", 3);
    }

    @EventListener
    @Order(2)
    public void syncListener2(EventType2 event) {
        EventUtils.print(event, "syncListener2", 2);
    }

    @EventListener
    @Order(1)
    public void syncListener3(EventType2 event) {
        EventUtils.print(event, "syncListener3", 1);
    }

    @EventListener
    @Order(0)
    public void syncListener4(EventType2 event) {
        throw new IllegalStateException();
    }
}
