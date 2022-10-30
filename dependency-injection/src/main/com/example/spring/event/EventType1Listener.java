package com.example.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;

/**
 * @author liuwenxue
 * @date 2022-10-30
 */
public class EventType1Listener implements SmartApplicationListener {
    @Override
    public boolean supportsEventType(final Class<? extends ApplicationEvent> eventType) {
        return eventType == EventType1.class;
    }
    @Override
    public boolean supportsSourceType(final Class<?> sourceType) {
        return sourceType == String.class;
    }


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        EventUtils.print(event, "onApplicationEvent");
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
