package com.example.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.util.ErrorHandler;

import java.util.Objects;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
* @author liuwenxue
* @date 2022-10-30
*/

@Configuration
@EnableAsync
public class EventConfiguration {
    private ExecutorService executorService;

    @Bean
    AutowiredBusiness autowiredBusiness() {
        return new AutowiredBusiness();
    }

    @Bean
    AwareBusiness awareBusiness() {
        return new AwareBusiness();
    }

    @Bean
    EventType2Listener eventType2Listener() {
        return new EventType2Listener();
    }

    @Bean
    EventType1Listener eventType1Listener() {
        return new EventType1Listener();
    }

    @Bean(name = "taskExecutor")
    public ExecutorService myTaskExecutor() {
        return newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-thread-pool-a"));
    }

    public ErrorHandler errorHandler() {
        return t -> System.err.println("spring event error: " + t.getMessage());
    }

    @Bean(name = AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster
                = new SimpleApplicationEventMulticaster();
        executorService = myTaskExecutor();
        eventMulticaster.setErrorHandler(errorHandler());
        return eventMulticaster;
    }

    @EventListener
    public void closeExecutor(ContextClosedEvent event) {
        System.out.println("receive close event, will close thread pool");
        if (Objects.nonNull(executorService)) {
            executorService.shutdown();
        }
    }

    @EventListener
    public void applicationEvent(ApplicationEvent event) {
        EventUtils.print(event, "allApplicationEvent");
    }
}