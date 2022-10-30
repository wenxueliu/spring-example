package com.example.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @author liuwenxue
 * @date 2022-10-30
 */
public class MyApplicationContextListener implements ApplicationListener<ApplicationContextEvent> {
    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        EventUtils.print(event, "MyApplicationContextListener");
    }
}